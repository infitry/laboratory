package com.infitry.laboratory.service.retry;

import com.infitry.laboratory.config.RetryConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = { RetryService.class, RetryConfig.class })
class RetryServiceTest {

    @Autowired
    private RetryService retryService;

    @Test
    @DisplayName("template 을 사용한 retry 를 테스트 합니다.")
    public void retryWithTemplateAlwaysFailing() {
        var maxAttempts = 5;
        retryService.retryWithTemplateAlwaysFailing(maxAttempts);
    }

    @Test
    @DisplayName("annotation 을 사용한 retry 를 테스트 합니다.")
    public void retryWithAnnotationAlwaysFailing() {
        assertThrows(IllegalStateException.class, () -> retryService.retryWithAnnotation(), "재시도 후 IllegalStateException 이 발생 합니다.");
    }

    @Test
    @DisplayName("annotation 을 사용한 noRetry 를 테스트 합니다.")
    public void noRetryWithAnnotation() {
        assertThrows(IllegalStateException.class, () -> retryService.noRetryWithAnnotation(), "다른 Exception 에는 재시도 후 IllegalStateException 이 발생 합니다.");
    }

    @Test
    @DisplayName("recover method 의 메시지가 출력되어야 한다.")
    public void retryWithRecoverMethod() {
       retryService.retryWithRecoverMethod();
    }
}