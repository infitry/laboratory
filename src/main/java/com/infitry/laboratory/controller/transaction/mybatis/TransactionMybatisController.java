package com.infitry.laboratory.controller.transaction.mybatis;

import com.infitry.laboratory.service.transaction.GroupService;
import com.infitry.laboratory.service.transaction.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-batis")
@RequiredArgsConstructor
public class TransactionMybatisController {

    private final GroupService groupService;
    private final MemberService memberService;

    @GetMapping("/transaction")
    public void lock() {
        memberService.myBatisReadUncommitted();
    }

    @GetMapping("/update/group")
    public void updateGroup(String groupCode) {
        groupService.updateGroupByMybatis(groupCode);
    }
}
