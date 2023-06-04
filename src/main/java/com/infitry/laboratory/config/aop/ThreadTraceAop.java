package com.infitry.laboratory.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class ThreadTraceAop {

    @Pointcut("@annotation(com.infitry.laboratory.config.aop.ThreadTrace)")
    private void threadTrace(){}

    @Around("threadTrace()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("START : " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.info("END : " + joinPoint + " " + timeMs + " ms");
        }
    }
}
