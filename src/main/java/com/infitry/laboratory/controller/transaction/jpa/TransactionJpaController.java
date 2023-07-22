package com.infitry.laboratory.controller.transaction.jpa;

import com.infitry.laboratory.service.transaction.FirstTransactionService;
import com.infitry.laboratory.service.transaction.SecondTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions/jpa")
@RequiredArgsConstructor
public class TransactionJpaController {
    private final FirstTransactionService firstTransactionService;
    private final SecondTransactionService secondTransactionService;

    @GetMapping("/second")
    public void executeSecondTransaction() {
        secondTransactionService.readUncommittedForJpa();
    }

    @GetMapping("/first")
    public void executeFirstTransaction(String groupCode) {
        firstTransactionService.readUncommittedFirstTransactionForJpa(groupCode);
    }
}
