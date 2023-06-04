package com.infitry.laboratory.config;

import com.infitry.laboratory.config.aop.ThreadTraceAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

    @Bean
    public ThreadTraceAop threadTraceAop() {
        return new ThreadTraceAop();
    }
}
