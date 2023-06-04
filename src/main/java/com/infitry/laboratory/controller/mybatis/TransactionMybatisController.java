package com.infitry.laboratory.controller.mybatis;

import com.infitry.laboratory.service.GroupService;
import com.infitry.laboratory.service.MemberService;
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
        memberService.insertMemberByMybatis();
    }

    @GetMapping("/update/group")
    public void updateGroup(String groupCode) {
        groupService.updateGroupByMybatis(groupCode);
    }
}
