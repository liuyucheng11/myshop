package com.my.shop.utils;

/**
 * <p>工具帮助类</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-25  10:09
 * @version 1.0
 */
public class HelperUtils {

    /**
     *
     * @param name method name
     * @return
     */
    public static String methodUpperFirstChar(String name) {
        if (Character.isUpperCase(name.charAt(0))) {
            return name;
        } else {
            return Character.toUpperCase(name.charAt(0)) + name
                    .substring(1);
        }
    }
}
