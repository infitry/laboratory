package com.infitry.laboratory.config.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class RedisHashAop {
    private final RedisTemplate<String, Object> redisTemplate;
    @Pointcut("@annotation(com.infitry.laboratory.config.aop.HashesCacheable)")
    private void hashesCacheable(){}

    @Around("hashesCacheable()")
    public Object execute(final ProceedingJoinPoint joinPoint) throws Throwable {
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        var cacheInfo = methodSignature.getMethod().getAnnotation(HashesCacheable.class);
        var hashKey = Arrays.stream(joinPoint.getArgs())
                .map(String::valueOf)
                .collect(Collectors.joining("_"));

        var returnType = methodSignature.getReturnType();
        var cacheGroupKey = cacheInfo.cacheName();

        HashOperations<String, Object, Object> redisHashOps = redisTemplate.opsForHash();
        var cacheData = redisHashOps.get(cacheGroupKey, hashKey);
        var cache = Optional.ofNullable(returnType.cast(cacheData));
        if (cache.isPresent()) {
            return cache.get();
        }

        Object result = joinPoint.proceed();
        redisHashOps.putIfAbsent(cacheGroupKey, hashKey, result);
        redisTemplate.expire(cacheGroupKey, cacheInfo.ttl(), cacheInfo.timeUnit());
        return result;
    }

    @Pointcut("@annotation(com.infitry.laboratory.config.aop.HashesCacheEvict)")
    private void hashesCacheEvict(){}

    @Around("hashesCacheEvict()")
    public Object evict(final ProceedingJoinPoint joinPoint) throws Throwable {
        var proceed = joinPoint.proceed();
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        var cacheInfo = methodSignature.getMethod().getAnnotation(HashesCacheEvict.class);
        var hashOps = redisTemplate.opsForHash();
        hashOps.getOperations().delete(cacheInfo.cacheName());
        return proceed;
    }
}
