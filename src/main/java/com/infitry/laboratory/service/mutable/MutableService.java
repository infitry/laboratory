package com.infitry.laboratory.service.mutable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MutableService {

    private String testString;
    private int testInt;
    private Integer testInteger;
    private long testLong;
    private Long testReferenceLong;
    private boolean testBoolean;


    public void test(String test, boolean testBool) {
        var threadId = Thread.currentThread().getName();
        log.info("==============ID: {} START===========", threadId);
        setData(test, testBool);
        printData(String.valueOf(threadId));
        log.info("==============ID: {} END===========\n", threadId);

    }

    private void setData(String test, boolean testBool) {
        testString = test;
        testInt = Integer.parseInt(test);
        testInteger = Integer.parseInt(test);
        testBoolean = testBool;
        testLong = Long.parseLong(test);
        testReferenceLong = Long.parseLong(test);
    }

    private void printData(String threadId) {
        log.info("[{}] testString : {}", threadId, testString);
        log.info("[{}] testInt : {}", threadId, testInt);
        log.info("[{}] testInteger : {}", threadId, testInteger);
        log.info("[{}] testBoolean : {}", threadId, testBoolean);
        log.info("[{}] testLong : {}", threadId, testLong);
        log.info("[{}] testReferenceLong : {}", threadId, testReferenceLong);
    }
}
