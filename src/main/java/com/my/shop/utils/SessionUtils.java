package com.my.shop.utils;

import com.my.shop.context.RequestContext;
import com.my.shop.context.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * <p>Session操作工具类基于redis控制</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-22  16:51
 * @version 1.0
 */
public class SessionUtils {
    private static volatile RedisUtil redisUtil;
    private static final long ONE_DAY = 24 * 3600;
    private static final long TEN_DAY = 24 * 3600 * 10;
    private static final Logger logger = LoggerFactory.getLogger(SessionUtils.class);


    public static Session getSession(String id) {
        RedisUtil redisUtil = getRedis();
        if (StringUtils.isEmpty(id)) {
            return getSession();
        }
        Session session = (Session) redisUtil.get(id);
        if (session == null) {
            return getSession();
        } else {
            return session;
        }
    }

    public static Session getSession() {
        return new Session();
    }

    private static void initRedis() {
        if (redisUtil == null) {
            synchronized (SpringContextUtil.class) {
                if (redisUtil == null) {
                    logger.info("【------开始redis注入------】");
                    redisUtil = (RedisUtil) SpringContextUtil.getBean("redis");
                    logger.info("【------redis注入完成------】");
                }
            }
        }
    }

    private static RedisUtil getRedis() {
        if (redisUtil == null) {
            initRedis();
        }
        return redisUtil;
    }

    /**
     * 刷新 session 有价值的 10天，无价值的1天!
     *
     * @param session
     */
    public static void refresh(Session session) {
        setRedisSession(session);
        if (session.getLoginUser() == null) {
            getRedis().expire(session.getId(), ONE_DAY);
        } else {
            getRedis().expire(session.getId(), TEN_DAY);
        }
    }

    public static void setRedisSession(Session session) {
        getRedis().set(session.getId(), session);
    }

    /**
     * redis
     *
     * @param key
     * @param expireTime
     */
    public static boolean checkToken(String key, long expireTime) {
        return getRedis().setIfAbsent(key, expireTime);
    }

    /**
     * 刷新锁
     *
     * @param key
     * @param expireTime
     */
    public static void expireToken(String key, long expireTime) {
        getRedis().expire(key, expireTime);
    }

    /**
     * 删除锁
     * @param key
     */
    public static void delToken(String key) {
        getRedis().del(key);
    }

    public static String generateKey(){
        String id = RequestContext.getRequestLocal().getSession().getLoginUser().getId().toString();
        return id + "_" + RequestContext.getAnnotationConfig().getUri();
    }

}
