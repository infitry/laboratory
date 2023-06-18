package com.infitry.laboratory.service;

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
public class MemberService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommitted() {
        var jpaIsolation = groupRepository.findById(1L);
        log.info("jpa group : {}", jpaIsolation);
    }

    @ThreadTrace
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void myBatisReadUncommitted() {
        var myBatisIsolation = groupMapper.findById(1L);
        log.info("mybatis group : {}", myBatisIsolation);
    }
}
