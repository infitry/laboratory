package com.infitry.laboratory.service.racecondition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class RaceConditionService {

    public void raceCondition(Long millis) throws InterruptedException {
        // 뭔가 많이한다.
        Thread.sleep(millis);
    }
}
