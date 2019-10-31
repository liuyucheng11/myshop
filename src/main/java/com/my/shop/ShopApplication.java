package com.my.shop;

import com.my.shop.config.command.StartCommand;
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
       // new StartCommand(args);
        SpringApplication.run(ShopApplication.class, args);
    }


}
