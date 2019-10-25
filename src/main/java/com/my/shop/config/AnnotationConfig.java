package com.my.shop.config;

import com.my.shop.config.annotation.MonitorConfig;
import com.my.shop.config.annotation.PermissionConfig;
import com.my.shop.config.annotation.TokenConfig;
import com.my.shop.utils.HelperUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author liu.yucheng
 * Date: 2019-10-22  14:42
 * @version 1.0
 */
@Data
public class AnnotationConfig {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationConfig.class);
    private static final String SETTER = "set";
    private MonitorConfig monitorConfig;
    private PermissionConfig permissionConfig;
    private TokenConfig tokenConfig;
    //请求URI
    private String uri;
    //请求是否获得锁
    private boolean lock = false;


    public AnnotationConfig builder(Annotation annotation) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(annotation.annotationType())) {
                try {
                    String setMethodName = SETTER + HelperUtils.methodUpperFirstChar(field.getName());
                    Method setMethod = this.getClass().getMethod(setMethodName, annotation.annotationType());
                    setMethod.invoke(this, annotation);
                } catch (Exception e) {
                    logger.error("反射设值失败!");
                }
            }
        }
        return this;
    }
}
