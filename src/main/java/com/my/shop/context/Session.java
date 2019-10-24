package com.my.shop.context;

import com.my.shop.utils.RedisUtil;
import com.my.shop.utils.SessionUtils;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * <p>基于redis实现会话管理</p>
 */
@Data
public class Session {
    private String id;
    private LoginUser loginUser;
    private String token;
    private RedisUtil redisUtil;

    public Session() {
        this.id = UUID.randomUUID().toString() + new Date().getTime();
        SessionUtils.setRedisSession(this);
    }

    public void refresh() {
        SessionUtils.refresh(this);
    }


}