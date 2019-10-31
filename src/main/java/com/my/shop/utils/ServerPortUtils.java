package com.my.shop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

/**
 * <p>启动端口工具类</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-28  10:39
 * @version 1.0
 */
public class ServerPortUtils {
    private static final Logger logger = LoggerFactory.getLogger(ServerPortUtils.class);

    private static final int max = 65535;
    private static final int min = 2000;
    private static Random random = new Random();

    /**
     * Desc:随机获取可用端口
     *
     * @return int
     */
    public static int getAvailablePort() {
        int port = random.nextInt(max) % (max - min + 1) + min;
        boolean using = isLocalePortUsing(port);
        if (using) {
            return getAvailablePort();
        }
        return port;
    }

    /**
     * Desc:校验端口可用性,不需要异常处理
     *
     * @param host 主机
     * @param port 端口
     * @return
     */
    private static boolean isPortUsing(String host, int port) {
        boolean flag = false;
        try {
            InetAddress theAddress = InetAddress.getByName(host);
            Socket socket = new Socket(theAddress, port);
            socket.close();
            flag = true;
        } catch (Exception e) {
            logger.info("Socket未能通信!端口未被占用");
        }
        return flag;
    }

    private static boolean isLocalePortUsing(int port) {
        boolean flag = true;
        flag = isPortUsing("127.0.0.1", port);
        return flag;
    }
}
