package com.my.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = {"com.my.shop"})
@ServletComponentScan(basePackages = {"com.my.shop.config"})
@MapperScan(basePackages = {"com.my.shop.modules.*.dao"})
/**
 * <p>启动类</p>
 */
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
    public static final String transactionExecution = "execution (* com.shop.modules.*.service.impl.*.*(..))";
    @Autowired
    private DataSource dataSource;
    //声明式事务
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(transactionExecution);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        Properties attributes = new Properties();
        attributes.setProperty("get*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        attributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor txAdvice = new TransactionInterceptor(new DataSourceTransactionManager(dataSource), attributes);
        advisor.setAdvice(txAdvice);
        return advisor;
    }

}
