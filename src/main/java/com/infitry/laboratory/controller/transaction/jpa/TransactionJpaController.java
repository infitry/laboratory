package com.infitry.laboratory.controller.transaction.jpa;

import com.infitry.laboratory.service.transaction.isolation.FirstTransactionService;
import com.infitry.laboratory.service.transaction.isolation.SecondTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions/isolation/jpa")
@RequiredArgsConstructor
public class TransactionJpaController {
    private final FirstTransactionService firstTransactionService;
    private final SecondTransactionService secondTransactionService;

    @GetMapping("/read-uncommitted/first")
    public void readUncommittedFirstTransaction(String groupCode) {
        firstTransactionService.readUncommittedForJpa(groupCode);
    }

    @GetMapping("/read-uncommitted/second")
    public void readUncommittedSecondTransaction() {
        secondTransactionService.readUncommittedForJpa();
    }

    @GetMapping("/read-committed/first")
    public void readCommittedFirstTransaction(String groupCode) {
        firstTransactionService.readCommittedForJpa(groupCode);
    }
    @GetMapping("/read-committed/second")
    public void readCommittedSecondTransaction() {
        secondTransactionService.readCommittedForJpa();
    }

    @GetMapping("/repeatable-read/first")
    public void repeatableReadFirstTransaction(String groupCode) {
        firstTransactionService.repeatableReadForJpa(groupCode);
    }
    @GetMapping("/repeatable-read/second")
    public void repeatableReadSecondTransaction() {
        secondTransactionService.repeatableReadForJpa();
    }

    @GetMapping("/serializable/first")
    public void serializableFirstTransaction(String groupCode) {
        firstTransactionService.serializableForJpa(groupCode);
    }
    @GetMapping("/serializable/second")
    public void serializableSecondTransaction() {
        secondTransactionService.serializableForJpa();
    }
}
