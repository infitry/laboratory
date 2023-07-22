package com.infitry.laboratory.controller.transaction.mybatis;

import com.infitry.laboratory.service.transaction.FirstTransactionService;
import com.infitry.laboratory.service.transaction.SecondTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions/my-batis")
@RequiredArgsConstructor
public class TransactionMybatisController {

    private final FirstTransactionService firstTransactionService;
    private final SecondTransactionService secondTransactionService;

    @GetMapping("/second")
    public void executeSecondTransaction() {
        secondTransactionService.readUncommittedForMyBatis();
    }

    @GetMapping("/first")
    public void executeFirstTransaction(String groupCode) {
        firstTransactionService.readUncommittedFirstTransactionForMybatis(groupCode);
    }
}
