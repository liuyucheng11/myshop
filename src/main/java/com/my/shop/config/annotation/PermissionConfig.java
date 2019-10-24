package com.my.shop.config.annotation;

import java.lang.annotation.*;

/**
 * <p>权限需要的权限缓存在needPermission中拦截器</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-23  9:29
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionConfig {

    String[] needPermission() default {};

}
