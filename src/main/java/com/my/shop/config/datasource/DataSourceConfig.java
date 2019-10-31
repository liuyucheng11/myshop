package com.my.shop.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>数据源配置 一写 多读</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-31  11:14
 * @version 1.0
 */
@Configuration
public class DataSourceConfig {

    @Bean("masterDataSource")
    @ConfigurationProperties("spring.datasource.master")
    public DataSource getMasterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave1DataSource")
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource getSlave1Source() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave2DataSource")
    @ConfigurationProperties("spring.datasource.slave2")
    public DataSource getSlave2Source() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave3DataSource")
    @ConfigurationProperties("spring.datasource.slave3")
    public DataSource getSlave3Source() {
        return DataSourceBuilder.create().build();
    }

    @Bean("myDataSource")
    public DataSource initDataSource(ApplicationContext ac) {
        DataSource master = (DataSource) ac.getBean("masterDataSource");
        DataSource slave1DataSource = (DataSource) ac.getBean("slave1DataSource");
        DataSource slave2DataSource = (DataSource) ac.getBean("slave2DataSource");
        DataSource slave3DataSource = (DataSource) ac.getBean("slave3DataSource");
        MyRoutingDataSource proxy = new MyRoutingDataSource();
        proxy.setDefaultTargetDataSource(master);
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.MASTER, master);
        targetDataSource.put(DataSourceType.SLAVE1, slave1DataSource);
        targetDataSource.put(DataSourceType.SLAVE2, slave2DataSource);
        targetDataSource.put(DataSourceType.SLAVE3, slave3DataSource);
        proxy.setTargetDataSources(targetDataSource);
        return proxy;
    }

}
