package com.my.shop.config.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.my.shop.config.datasource.Master) " +
            "&& (execution(* com.my.shop.modules.*.service.impl.*.select*(..)) " +
            "|| execution(* com.my.shop.modules.*.service.impl.*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.my.shop.config.datasource.Master)" +
            "|| execution(* com.my.shop.modules.*.service.impl.*.insert*(..)) " +
            "|| execution(* com.my.shop.modules.*.service.impl.*.add*(..)) " +
            "|| execution(* com.my.shop.modules.*.service.impl.*.update*(..)) " +
            "|| execution(* com.my.shop.modules.*.service.impl.*.edit*(..)) " +
            "|| execution(* com.my.shop.modules.*.service.impl.*.delete*(..)) " +
            "|| execution(* com.my.shop.modules.*.service.impl.*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}