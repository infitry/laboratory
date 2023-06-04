package com.infitry.laboratory.service;

import com.infitry.laboratory.config.aop.ThreadTrace;
import com.infitry.laboratory.entity.MemberGroup;
import com.infitry.laboratory.persistence.jpa.GroupRepository;
import com.infitry.laboratory.persistence.jpa.MemberRepository;
import com.infitry.laboratory.persistence.mybatis.GroupMapper;
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
    private final GroupMapper groupMapper;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveGroup(MemberGroup memberGroup) {
        groupRepository.save(memberGroup);
    }

    @Transactional(readOnly = true)
    public void findAllGroup() {
        var allGroups = groupRepository.findAll();
        log.info("All group : {}", allGroups);
        var findMember = memberRepository.findById(4L);
        log.info("readOnly find member : {}", findMember);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateGroup(String groupCode) {
        var findGroup = groupRepository.findById(1L).orElseThrow(NoSuchElementException::new);
        findGroup.setCode(groupCode);
        groupRepository.save(findGroup);
        log.info("updated group code : {}", groupCode);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateGroupByMybatis(String groupCode) {
        var findGroup = groupMapper.findById(1L);
        findGroup.setCode(groupCode);
        groupMapper.updateGroup(findGroup);
        log.info("updated group code : {}", groupCode);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
