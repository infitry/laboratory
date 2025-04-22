package com.infitry.laboratory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.retry.support.RetryTemplateBuilder;

@EnableRetry // 없으면 @Retryable 어노테이션이 동작하지 않습니다.
@Configuration
public class RetryConfig {
    @Bean
    public RetryTemplate retryTemplate() {
        var exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(500);   // 재시도 인터벌 시작 값
        exponentialBackOffPolicy.setMultiplier(2.0);        // 재시도 마다 2배 씩 늘어난다
        exponentialBackOffPolicy.setMaxInterval(5000);      // 최대 인터벌 값

        return new RetryTemplateBuilder()
                .maxAttempts(3) // 최대 재시도 값
                .notRetryOn(IllegalArgumentException.class) // 재시도 제외 할 Exception
                .customBackoff(exponentialBackOffPolicy)
                .build();
    }
}
