package com.my.shop.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * <p>拦截器枚举</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-24  11:26
 * @version 1.0
 */
public enum InterceptorEnum {
    COMMON("常规拦截器", CommonInterceptor.class, null),
    LOGIN("登录拦截器", LoginInterceptor.class, "未登录,请登录后访问!"),
    PERMISSION("权限拦截器", PermissionInterceptor.class, "当前权限不足！"),
    TOKEN("token拦截器", TokenInterceptor.class, "后台token校验异常!请稍后重试！");
    private String name;
    private Class<? extends HandlerInterceptor> handlerInterceptor;
    private String reason;

    InterceptorEnum(String name, Class<? extends HandlerInterceptor> handlerInterceptor, String reason) {
        this.name = name;
        this.handlerInterceptor = handlerInterceptor;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public Class<? extends HandlerInterceptor> getHandlerInterceptor() {
        return handlerInterceptor;
    }

    public String getReason() {
        return reason;
    }

    /**
     * 根据clazz 信息返回
     *
     * @param clazz
     * @return
     */
    public static InterceptorEnum getEnumByClass(Class clazz) {
        for (InterceptorEnum val : InterceptorEnum.values()) {
            if (val.getHandlerInterceptor().equals(clazz)) {
                return val;
            }
        }
        return null;
    }
}
