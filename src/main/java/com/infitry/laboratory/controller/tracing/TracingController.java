package com.infitry.laboratory.controller.tracing;

import com.infitry.laboratory.service.tracing.TracingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tracing")
public class TracingController {

    private final Executor executor;
    private final TracingService tracingService;

    @GetMapping("/test")
    public void testTracing() {
        tracingService.print();
        executor.execute(() -> log.info("executors 에 의한 스레드입니다."));
        tracingService.asyncPrint();
    }
}
