package com.infitry.laboratory.controller.transaction.jpa;

import com.infitry.laboratory.entity.MemberGroup;
import com.infitry.laboratory.service.GroupService;
import com.infitry.laboratory.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
@RequiredArgsConstructor
public class TransactionTestController {
    private final GroupService groupService;
    private final MemberService memberService;

    @GetMapping("/transaction")
    public void lock() {
        memberService.readUncommitted();
    }

    @GetMapping("/insert/groups")
    public void insertGroup() {
        groupService.saveGroup(new MemberGroup(null, "신규그룹", "A1"));
    }

    @GetMapping("/groups")
    public void getGroups() {
        groupService.findAllGroup();
    }

    @GetMapping("/update/group")
    public void updateGroup(String groupCode) {
        groupService.updateGroup(groupCode);
    }
}
