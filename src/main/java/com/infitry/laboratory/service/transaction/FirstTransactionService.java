package com.infitry.laboratory.service.transaction;

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
@Transactional(readOnly = true)
public class FirstTransactionService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveGroup(MemberGroup memberGroup) {
        groupRepository.save(memberGroup);
    }

    public void findAllGroup() {
        var allGroups = groupRepository.findAll();
        log.info("All group : {}", allGroups);
        var findMember = memberRepository.findById(4L);
        log.info("readOnly find member : {}", findMember);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommittedForJpa(String groupCode) {
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
    public void readUncommittedForMybatis(String groupCode) {
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

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommittedForJpa(String groupCode) {
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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommittedForMybatis(String groupCode) {
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

    @ThreadTrace
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableReadForJpa(String groupCode) {
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
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableReadForMybatis(String groupCode) {
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

    @ThreadTrace
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void serializableForJpa(String groupCode) {
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
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void serializableForMybatis(String groupCode) {
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
