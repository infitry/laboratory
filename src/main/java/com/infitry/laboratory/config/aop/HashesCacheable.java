package com.infitry.laboratory.config.aop;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HashesCacheable {
    @AliasFor("cacheName")
    String value() default "";
    @AliasFor("value")
    String cacheName() default "";
    long ttl() default -1L;
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
