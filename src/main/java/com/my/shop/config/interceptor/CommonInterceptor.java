package com.my.shop.config.interceptor;

import com.my.shop.context.RequestContext;
import com.my.shop.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>基于拦截器实现权限，登录，token控制</p>
 * <p>统一处理回收资源</p>
 * <p>不要修改此类</p>
 * <p>打印拦截器日志</p>
 *
 * @see com.my.shop.config.interceptor
 * @author liu.yucheng
 * Date: 2019-10-23  9:15
 * @version 1.0
 */
public class CommonInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            RequestContext.Interceptor interceptor = RequestContext.getInterceptor();
            if (interceptor != null) {
                InterceptorEnum interceptorEnum = InterceptorEnum.getEnumByClass(interceptor.getInterceptor().getClass());
                assert interceptorEnum != null;
                logger.info("拦截器【{}】拒绝了【{}】请求，原因【{}】",
                        interceptorEnum.getName(),
                        RequestContext.getAnnotationConfig().getUri(),
                        interceptorEnum.getReason());
            }
        } catch (Throwable e) {
            logger.error("拦截器拦截异常！");
        } finally {
            //默认false
            if (RequestContext.getAnnotationConfig().isLock()) {
                SessionUtils.delToken(SessionUtils.generateKey());
            }
            //释放资源
            RequestContext.destroy();
        }
    }
}
