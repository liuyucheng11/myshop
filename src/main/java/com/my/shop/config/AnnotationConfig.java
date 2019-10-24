package com.my.shop.config;

import com.my.shop.config.annotation.MonitorConfig;
import com.my.shop.config.annotation.PermissionConfig;
import com.my.shop.config.annotation.TokenConfig;
import lombok.Data;

/**
 * @author liu.yucheng
 * Date: 2019-10-22  14:42
 * @version 1.0
 */
@Data
public class AnnotationConfig {
    private MonitorConfig monitorConfig;
    private PermissionConfig permissionConfig;
    private TokenConfig tokenConfig;
    //请求URI
    private String uri;
    //请求是否获得锁
    private boolean lock = false;
}
