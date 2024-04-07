package com.infitry.laboratory.service.tracing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TracingService {

    public void print() {
        log.info("기존 스레드를 입니다.");
    }
    @Async
    public void asyncPrint() {
        log.info("@Async 에 의한 스레드 입니다.");
    }
}
