package com.infitry.laboratory.shared;

import com.infitry.laboratory.shared.util.NumberUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@UtilityClass
public class TaskHelper {
    public void simulateHeavyTaskFailure(RuntimeException exception) {
        printLogWithThread("실행 중 : {}");
        sleep(2000);
        printLogWithThread("완료 : {}");
        throw exception;
    }

    public void doHeavyTaskMaybeFail(int retry) {
        printLogWithThread("실행 중 : {}");
        sleep(2000);
        var random = new Random();
        var randomNumber = random.nextInt(retry);
        if (NumberUtil.isEven(randomNumber)) {
            throw new IllegalStateException();
        }
        printLogWithThread("완료 : {}");
    }

    public void doHeavyTask() {
        printLogWithThread("실행 중 : {}");
        sleep(3000);
        printLogWithThread("완료 : {}");
    }

    private static void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignore) {}
    }

    private static void printLogWithThread(String s) {
        log.info(s, Thread.currentThread().getName());
    }
}
