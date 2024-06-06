package com.infitry.laboratory.service.transaction.connectionpool;

import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectionPoolFacade {
    private final ConnectionPoolService connectionPoolService;
    private final HikariPoolMXBean hikariPoolMXBean;
    private static final int LATENCY = 3000;

    public void doSomethingBefore() throws Exception {
        printCurrentConnectionPool("1");
        sleep();
        printCurrentConnectionPool("2");
        connectionPoolService.whenGetConnectionInTransactional();
        printCurrentConnectionPool("3");
    }

    public void doSomethingAfter() throws Exception {
        printCurrentConnectionPool("1");
        connectionPoolService.whenGetConnectionInTransactional();
        printCurrentConnectionPool("2");
        sleep();
        printCurrentConnectionPool("3");
    }

    private static void sleep() throws InterruptedException {
        log.info("start sleep!!!");
        Thread.sleep(LATENCY);
        log.info("end sleep!!");
    }

    private void printCurrentConnectionPool(String id) {
        log.info("{}, (total={}, active={}, idle={}, waiting={})", id,
                hikariPoolMXBean.getTotalConnections(), hikariPoolMXBean.getActiveConnections(),
                hikariPoolMXBean.getIdleConnections(), hikariPoolMXBean.getThreadsAwaitingConnection());
    }
}
