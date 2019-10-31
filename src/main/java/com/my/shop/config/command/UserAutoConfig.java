package com.my.shop.config.command;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liu.yucheng
 * Date: 2019-10-28  11:23
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(UserProperties.class)
public class UserAutoConfig {
    @Bean
    @ConditionalOnProperty(prefix = "spring.user", value = "enable", havingValue = "true")
    public UserClient userClient(UserProperties userProperties) {
        return new UserClient(userProperties);
    }
}
