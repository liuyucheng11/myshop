package com.my.shop.config.command;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liu.yucheng
 * Date: 2019-10-28  11:20
 * @version 1.0
 */
@Data
@ConfigurationProperties("spring.user")
public class UserProperties {
    private String name;
}
