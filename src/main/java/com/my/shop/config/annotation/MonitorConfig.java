package com.my.shop.config.annotation;

import java.lang.annotation.*;

/**
 * <p>自定义注解 标注在@RequestMapping修饰方法上</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-22  14:21
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MonitorConfig {
    boolean needLogin() default false;

    String Msg() default "未登录！";


}
