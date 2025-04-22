package com.infitry.laboratory.service.retry;

import com.infitry.laboratory.shared.TaskHelper;
import com.infitry.laboratory.shared.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * retry, recover 를 활용해 재시도 및 복구 테스트를 합니다.
 * RetryTemplate 을 활용한 방법과 @Retryable 어노테이션을 활용한 방법으로 테스트 합니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RetryService {

    /**
     * retry template 을 이용한 재시도 를 확인할 수 있습니다.
     * @param maxAttempts 총 시도 횟 수 (첫 시도 + 재시도 횟 수)
     */
    public void retryWithTemplateAlwaysFailing(int maxAttempts) {
        var exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(500);   // 재시도 인터벌 시작 값
        exponentialBackOffPolicy.setMultiplier(2.0);        // 재시도 마다 2배 씩 늘어난다
        exponentialBackOffPolicy.setMaxInterval(5000);      // 최대 인터벌 값

        var retryTemplate = new RetryTemplateBuilder()
                .maxAttempts(maxAttempts)
                .customBackoff(exponentialBackOffPolicy)
                .build();

        var result = retryTemplate.execute(context -> {
            TaskHelper.simulateHeavyTaskFailure(new IllegalStateException("The operation should be retryable."));
            return true;
        }, context -> {
            log.error("All retry attempts failed.");
            return false;
        });

        log.info("result = {}", result);
    }

    /**
     * 어노테이션을 활용해 재시도 전략을 수립 합니다.
     * 총 2번의 시도를 고정된 2초 간격 으로 IllegalStateException 일 경우 재시도 합니다.
     */
    @Retryable(
        maxAttempts = 2,
        retryFor = { IllegalStateException.class },
        backoff = @Backoff(delay = 2000)
    )
    public void retryWithAnnotation() {
        TaskHelper.simulateHeavyTaskFailure(new IllegalStateException("The operation should be retryable."));
    }


    /**
     * 어노테이션을 활용해 재시도 전략을 수립 합니다.
     * 2 번의 시도를 하게 설정 하였지만, IllegalStateException 예외에는 재시도하지 않습니다.
     */
    private final AtomicInteger number = new AtomicInteger(0);
    @Retryable(
        maxAttempts = 2,
        noRetryFor = { IllegalStateException.class },
        backoff = @Backoff(delay = 1000)
    )
    public void noRetryWithAnnotation() {
       if (!NumberUtil.isEven(number.addAndGet(1))) {
           TaskHelper.simulateHeavyTaskFailure(new IllegalArgumentException("The operation should be retryable."));
       } else {
           TaskHelper.simulateHeavyTaskFailure(new IllegalStateException("The operation should not be retryable."));
       }
    }

    /**
     * recover 메소드를 활용합니다.
     * 2번의 시도를 하고, recover 메소드를 호출하고 종료합니다.
     */
    @Retryable(
            maxAttempts = 2,
            retryFor = { IllegalStateException.class },
            backoff = @Backoff(delay = 1000)
    )
    public void retryWithRecoverMethod() {
        TaskHelper.simulateHeavyTaskFailure(new IllegalStateException("The operation should not be retryable."));
    }

    /**
     * method signature 로 recover 대상을 판단 합니다.
     */
    @Recover
    public void recoverMethod(IllegalStateException e) {
        log.error("The retry attempt has been recovered.", e);
    }
}
