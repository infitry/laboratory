package com.infitry.laboratory.controller.transaction.mybatis;

import com.infitry.laboratory.service.transaction.FirstTransactionService;
import com.infitry.laboratory.service.transaction.SecondTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions/isolation/my-batis")
@RequiredArgsConstructor
public class TransactionMybatisController {
    private final FirstTransactionService firstTransactionService;
    private final SecondTransactionService secondTransactionService;

    @GetMapping("/read-uncommitted/first")
    public void readUncommittedFirstTransaction(String groupCode) {
        firstTransactionService.readUncommittedForMybatis(groupCode);
    }

    @GetMapping("/read-uncommitted/second")
    public void readUncommittedSecondTransaction() {
        secondTransactionService.readUncommittedForMyBatis();
    }

    @GetMapping("/read-committed/first")
    public void readCommittedFirstTransaction(String groupCode) {
        firstTransactionService.readCommittedForMybatis(groupCode);
    }
    @GetMapping("/read-committed/second")
    public void readCommittedSecondTransaction() {
        secondTransactionService.readCommittedForMyBatis();
    }

    @GetMapping("/repeatable-read/first")
    public void repeatableReadFirstTransaction(String groupCode) {
        firstTransactionService.repeatableReadForMybatis(groupCode);
    }
    @GetMapping("/repeatable-read/second")
    public void repeatableReadSecondTransaction() {
        secondTransactionService.repeatableReadForMyBatis();
    }

    @GetMapping("/serializable/first")
    public void serializableFirstTransaction(String groupCode) {
        firstTransactionService.serializableForMybatis(groupCode);
    }
    @GetMapping("/serializable/second")
    public void serializableSecondTransaction() {
        secondTransactionService.serializableForMyBatis();
    }
}
