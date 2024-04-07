package com.infitry.laboratory.controller;

import com.infitry.laboratory.service.racecondition.RaceConditionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.ArrayList;

@SpringBootTest
class RaceConditionTest {

    @Autowired
    private RaceConditionService raceConditionService;

    @Test
    @DisplayName("경쟁상태 테스트")
    public void raceCondition() {
        var stopWatch = new StopWatch();
        stopWatch.start();
        int testCount = 100;
        var users = createUsers(testCount);
        users.forEach(this::runService);
        stopWatch.stop();

        System.out.println("total time = " + stopWatch.getTotalTimeSeconds() + " seconds");
    }

    @Test
    @DisplayName("경쟁상태 테스트 (병렬)")
    public void raceConditionForParallelStream() {
        var stopWatch = new StopWatch();
        stopWatch.start();
        int testCount = 100;

        var users = createUsers(testCount);
        users.parallelStream().forEach(this::runService);
        stopWatch.stop();

        System.out.println("total time = " + stopWatch.getTotalTimeSeconds() + " seconds");
    }

    private void runService(int user) {
        Long waitTimeMillis = 1000L;
        raceConditionService.raceCondition(waitTimeMillis);
        System.out.println("Users = " + user + " is OK");
    }

    private static ArrayList<Integer> createUsers(int testCount) {
        var users = new ArrayList<Integer>();
        for (int i = 0; i < testCount; i++) {
            users.add(i);
        }
        return users;
    }
}
