package com.infitry.laboratory.service;

import com.infitry.laboratory.config.aop.ThreadTrace;
import com.infitry.laboratory.entity.Member;
import com.infitry.laboratory.persistence.jpa.GroupRepository;
import com.infitry.laboratory.persistence.jpa.MemberRepository;
import com.infitry.laboratory.persistence.mybatis.GroupMapper;
import com.infitry.laboratory.persistence.mybatis.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void insertMember() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var group = groupRepository.findById(1L);
        log.info("find group : {}", group);
        memberRepository.save(new Member(null, "이름", "닉네임", "1111", 1));
        var savedMember = memberRepository.findById(4L);
        log.info("save member - {}", savedMember);
        var afterSaveGroup = groupRepository.findById(1L);
        log.info("after save group : {}", afterSaveGroup);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void insertMemberByMybatis() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var group = groupMapper.findById(1L);
        log.info("find group : {}", group);
        memberMapper.save(new Member(null, "이름", "닉네임", "1111", 1));
        var savedMember = memberMapper.findById(4L);
        log.info("save member - {}", savedMember);
        var afterSaveGroup = groupMapper.findById(1L);
        log.info("after save group : {}", afterSaveGroup);
    }
}
