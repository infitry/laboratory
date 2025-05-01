package com.infitry.laboratory.service.lock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RedisLockServiceTest {
    @Autowired
    private RedisLockService redisLockService;

    @Test
    @DisplayName("다른 스레드가 접근하지 못하게 LOCK 합니다. (Local redis 6379 port 필요)")
    public void noRetryLock() {
        // given
        var id = "lock-1";
        var threadCount = 5;
        var executor = Executors.newFixedThreadPool(threadCount);
        // when
        executor.submit(() -> redisLockService.decrease(id, Duration.ofSeconds(10L)));

        // then
        assertThrows(IllegalStateException.class, () -> redisLockService.decrease(id, Duration.ofSeconds(1L)), "IllegalStateException 이 발생해야 한다.");
    }
}