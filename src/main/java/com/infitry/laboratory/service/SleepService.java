package com.infitry.laboratory.service;

import com.infitry.laboratory.config.aop.ThreadTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SleepService {
    @ThreadTrace
    public void sleep(Long milliseconds) {
        try {
            Thread.sleep(milliseconds);
            log.info("일어났다!");
        } catch (Exception e) {
            log.error("자는 도중 에러 발생", e);
        }
    }
}
