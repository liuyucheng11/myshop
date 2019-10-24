package com.my.shop.config.annotation;

import java.lang.annotation.*;

/**
 * <p>防止重复提交</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-24  14:11
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenConfig {
    //同一请求重复提交时间
    int time() default 200;
}
