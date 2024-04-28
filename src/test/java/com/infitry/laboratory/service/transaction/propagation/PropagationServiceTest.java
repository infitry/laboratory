package com.infitry.laboratory.service.transaction.propagation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropagationServiceTest {

    @Autowired
    PropagationService propagationService;


    @Test
    @DisplayName("required 테스트")
    public void required() {
        propagationService.requiredTransaction();
    }

    @Test
    @DisplayName("requires new 테스트")
    public void requiresNew() {
        propagationService.requiresNewTransaction();
    }

    @Test
    @DisplayName("supports 테스트")
    public void supports() {
        propagationService.supportsTransaction();
    }

    @Test
    @DisplayName("not Supported 테스트")
    public void notSupported() {
        propagationService.notSupportedTransactional();
    }

    @Test
    @DisplayName("nested 테스트")
    public void nested() {
        propagationService.nestedTransactional();
    }

    @Test
    @DisplayName("mandatory 테스트")
    public void mandatory() {
        propagationService.mandatoryTransactional();
    }

    @Test
    @DisplayName("never 테스트")
    public void never() {
        propagationService.neverTransactional();
    }
}