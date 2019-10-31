package com.my.shop.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;



/**
 * <p>自定义查找数据源方式 获取数据库连接时调用此类 determineCurrentLookupKey方法从
 * resolvedDataSources中取出对应的连接</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-31  11:22
 * @version 1.0
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }


}
