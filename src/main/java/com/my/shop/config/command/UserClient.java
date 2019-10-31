package com.my.shop.config.command;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liu.yucheng
 * Date: 2019-10-28  11:25
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class UserClient {
    private UserProperties userProperties;

    private String getName(){
        return userProperties.getName();
    }
}
