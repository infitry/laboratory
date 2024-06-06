package com.infitry.laboratory.service.transaction.connectionpool;

import com.infitry.laboratory.persistence.jpa.MemberRepository;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConnectionPoolService {
    private final MemberRepository memberRepository;
    private final HikariPoolMXBean hikariPoolMXBean;

    @Transactional
    public void whenGetConnectionInTransactional() throws Exception {
        printCurrentConnectionPool();
        memberRepository.findAll();
        Thread.sleep(2000);
    }

    private void printCurrentConnectionPool() {
        log.info("in transaction, (total={}, active={}, idle={}, waiting={})",
                hikariPoolMXBean.getTotalConnections(), hikariPoolMXBean.getActiveConnections(),
                hikariPoolMXBean.getIdleConnections(), hikariPoolMXBean.getThreadsAwaitingConnection());
    }
}
