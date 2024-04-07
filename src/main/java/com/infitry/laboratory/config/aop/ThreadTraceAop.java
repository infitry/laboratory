package com.infitry.laboratory.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
public class ThreadTraceAop {

    @Pointcut("@annotation(com.infitry.laboratory.config.aop.ThreadTrace)")
    private void threadTrace(){}

    @Around("threadTrace()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        var stopWatch = new StopWatch();
        stopWatch.start();
        log.info("[START] ---- {} ----", joinPoint);
        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("[END] ---- {}, {} ms ----", joinPoint, stopWatch.getTotalTimeMillis());
        }
    }
}
