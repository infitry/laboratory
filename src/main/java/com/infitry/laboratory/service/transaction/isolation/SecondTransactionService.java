package com.infitry.laboratory.service.transaction.isolation;

import com.infitry.laboratory.config.aop.ThreadTrace;
import com.infitry.laboratory.persistence.jpa.GroupRepository;
import com.infitry.laboratory.persistence.mybatis.GroupMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecondTransactionService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommittedForJpa() {
        var jpaIsolation = groupRepository.findById(1L);
        log.info("jpa group : {}", jpaIsolation);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommittedForMyBatis() {
        var myBatisIsolation = groupMapper.findById(1L);
        log.info("mybatis group : {}", myBatisIsolation);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommittedForJpa() {
        var jpaIsolation = groupRepository.findById(1L);
        log.info("jpa group : {}", jpaIsolation);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommittedForMyBatis() {
        var myBatisIsolation = groupMapper.findById(1L);
        log.info("mybatis group : {}", myBatisIsolation);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableReadForJpa() {
        var jpaIsolation = groupRepository.findById(1L);
        log.info("jpa group : {}", jpaIsolation);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableReadForMyBatis() {
        var myBatisIsolation = groupMapper.findById(1L);
        log.info("mybatis group : {}", myBatisIsolation);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void serializableForJpa() {
        var jpaIsolation = groupRepository.findById(1L);
        log.info("jpa group : {}", jpaIsolation);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void serializableForMyBatis() {
        var myBatisIsolation = groupMapper.findById(1L);
        log.info("mybatis group : {}", myBatisIsolation);
    }
}
