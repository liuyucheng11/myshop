package com.my.shop.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class DBContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DBContextHolder.class);
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DataSourceType dbType) {
        contextHolder.set(dbType);
    }

    public static DataSourceType get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DataSourceType.MASTER);
        System.out.println("切换到master");
    }

    /**
     * 简单读负载均衡
     */
    public static void slave() {
        //  轮询
        int index = counter.getAndIncrement() % 3;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DataSourceType.SLAVE1);
            logger.info("【{}】", "切换到slave1");
            //System.out.println("切换到slave1");
        } else if (index == 1) {
            set(DataSourceType.SLAVE2);
            logger.info("【{}】", "切换到slave2");
            // System.out.println("切换到slave2");
        } else {
            set(DataSourceType.SLAVE3);
            logger.info("【{}】", "切换到slave3");
            // System.out.println("切换到slave3");
        }
    }

}