package com.my.shop.config.datasource;

import java.lang.annotation.*;

/**
 * <p>强制走写库</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-31  11:20
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Master {
}
