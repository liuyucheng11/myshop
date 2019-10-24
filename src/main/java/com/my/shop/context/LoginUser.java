package com.my.shop.context;

import lombok.Data;

import java.util.Set;

/**
 * @author liu.yucheng
 * Date: 2019-10-22  14:00
 * @version 1.0
 */
@Data
public class LoginUser {
    private Long id;

    private long level;

    private String mobile;

    /**
     * 登录用户所有权限
     */
    private Set<String> permission;

}
