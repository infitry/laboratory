package com.infitry.laboratory.config;

import io.micrometer.context.ContextExecutorService;
import io.micrometer.context.ContextScheduledExecutorService;
import io.micrometer.context.ContextSnapshot;
import io.micrometer.context.ContextSnapshotFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.*;

@EnableAsync
@Configuration(proxyBeanMethods = false)
public class AsyncConfig implements AsyncConfigurer, WebMvcConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return ContextExecutorService.wrap(Executors.newCachedThreadPool(), ContextSnapshot::captureAll);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new SimpleAsyncTaskExecutor(r -> new Thread(ContextSnapshotFactory.builder().build().captureAll().wrap(r))));
    }

    @Bean
    public ThreadPoolTaskScheduler taskExecutor() {
        var threadPoolTaskScheduler = new ThreadPoolTaskScheduler() {
            @Override
            public ScheduledExecutorService getScheduledExecutor() throws IllegalStateException {
                return ContextScheduledExecutorService.wrap(super.getScheduledExecutor());
            }
        };
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }
}