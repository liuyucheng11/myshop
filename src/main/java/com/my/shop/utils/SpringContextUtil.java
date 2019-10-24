package com.my.shop.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * <p>Spring上下文，在StartInitListener中初始化 applicationContext</p>
 *
 */
public class SpringContextUtil {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext1) throws BeansException {
        if (applicationContext == null) {
            synchronized (SpringContextUtil.class) {
                if (applicationContext == null) {
                    applicationContext = applicationContext1;
                }
            }
        }
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
