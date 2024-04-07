package com.infitry.laboratory.service.lock;

import com.infitry.laboratory.persistence.redis.RedisLockRepository;
import com.infitry.laboratory.service.racecondition.RaceConditionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisLockService {

    private final RaceConditionService raceConditionService;
    private final RedisLockRepository redisLockRepository;

    public void decrease(final String key, final Duration latency) {
        try {
            var isSaved = redisLockRepository.lock(key);
            var isLock = !isSaved;
            if (isLock) {
                throw new IllegalStateException("Unable to acquire lock");
            }
            raceConditionService.raceCondition(latency.toMillis());
        } finally {
            redisLockRepository.unlock(key);
        }
    }
}
