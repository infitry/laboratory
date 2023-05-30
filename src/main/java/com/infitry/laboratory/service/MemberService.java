package com.infitry.laboratory.service;

import com.infitry.laboratory.entity.Member;
import com.infitry.laboratory.persistence.GroupRepository;
import com.infitry.laboratory.persistence.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void insertMember() {
        log.info("Thread - {} Transaction Start", Thread.currentThread().getId());
        var group = groupRepository.findById(1L);
        memberRepository.save(new Member(null, "이름", "닉네임", "1111", 1));
        log.info("find group : {}", group);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var savedMember = memberRepository.findById(4L);
        log.info("save member - {}", savedMember);
        log.info("Thread - {} Transaction End", Thread.currentThread().getId());
    }
}
