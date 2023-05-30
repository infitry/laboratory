package com.infitry.laboratory.service;

import com.infitry.laboratory.entity.Member;
import com.infitry.laboratory.entity.MemberGroup;
import com.infitry.laboratory.persistence.GroupRepository;
import com.infitry.laboratory.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveGroup(MemberGroup memberGroup) {
        groupRepository.save(memberGroup);
    }

    @Transactional(readOnly = true)
    public void findAllGroup() {
        log.info("Thread - {} Transaction Start", Thread.currentThread().getId());
        var allGroups = groupRepository.findAll();
        log.info("All group : {}", allGroups);
        var findMember = memberRepository.findById(4L);
        log.info("readOnly find member : {}", findMember);
        log.info("Thread - {} Transaction End", Thread.currentThread().getId());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateGroup(String groupCode) {
        log.info("Thread - {} Transaction Start", Thread.currentThread().getId());
        var findGroup = groupRepository.findById(1L).orElseThrow(NoSuchElementException::new);
        findGroup.setCode(groupCode);
        groupRepository.save(findGroup);
        log.info("updated group code");
        log.info("Thread - {} Transaction End", Thread.currentThread().getId());
    }
}
