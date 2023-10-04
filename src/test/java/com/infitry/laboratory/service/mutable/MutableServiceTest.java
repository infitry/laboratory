package com.infitry.laboratory.service.mutable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MutableServiceTest {

    @Autowired
    MutableService mutableService;

    @Test
    @DisplayName("상태를 갖는 서비스 테스트")
    public void mutableServiceTester() {
        IntStream.range(0, 10)
                .parallel()
                .forEach(i -> mutableService.test(String.valueOf(i), i % 2 == 0));
    }
}