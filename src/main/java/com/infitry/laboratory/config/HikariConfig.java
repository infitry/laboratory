package com.infitry.laboratory.config;

import com.zaxxer.hikari.HikariPoolMXBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Configuration
public class HikariConfig {
    @Bean
    public HikariPoolMXBean hikariPoolMXBean() throws MalformedObjectNameException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("com.zaxxer.hikari:type=Pool (hikari)");
        return JMX.newMBeanProxy(mBeanServer, objectName, HikariPoolMXBean.class);
    }
}
