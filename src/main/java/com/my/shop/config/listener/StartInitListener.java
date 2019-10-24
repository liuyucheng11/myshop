package com.my.shop.config.listener;

import com.my.shop.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/** <p>初始化Spring后完成对工具类加载</p>
 * @author liu.yucheng
 * Date: 2019-10-22  11:18
 * @version 1.0
 */
@WebListener
public class StartInitListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(StartInitListener.class);

    /**
     *  @see com.my.shop.utils.SpringContextUtil
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
            logger.info("【startInitLister】开始启动");
            WebApplicationContext wac = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(sce.getServletContext());
            SpringContextUtil.setApplicationContext(wac);
            logger.info("【startInitLister】启动完成");
        }catch (Throwable e){
            logger.error("初始化Spring失败!");
        }
    }
}
