package com.my.shop.context;

import com.my.shop.config.AnnotationConfig;
import com.my.shop.config.annotation.MonitorConfig;
import com.my.shop.config.annotation.PermissionConfig;
import com.my.shop.config.annotation.TokenConfig;
import com.my.shop.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>请求线程上下文</p>
 *
 * @author liu.yucheng
 * Date: 2019-10-22  13:59
 * @version 1.0
 */
public class RequestContext {
    private static final Logger logger = LoggerFactory.getLogger(RequestContext.class);

    private static ThreadLocal<LoginUser> loginUser = new ThreadLocal<>();
    private static ThreadLocal<AnnotationConfig> annotationConfig = new ThreadLocal<>();
    //  private static ThreadLocal<Session> session = new ThreadLocal<>();
    private static ThreadLocal<RequestLocal> requestLocal = new ThreadLocal<>();
    //拦截器信息
    private static ThreadLocal<Interceptor> interceptor = new ThreadLocal<>();


    public static void initRequestContext(HttpServletRequest request) {
        RequestLocal val = new RequestLocal(request);
        requestLocal.set(val);
    }

    public static void initInterceptor(Interceptor val) {
        interceptor.set(val);
    }

    public static Interceptor getInterceptor() {
        return interceptor.get();
    }

    /**
     * Desc: <p>缓存方法注解信息供拦截器使用</p>
     *
     * @param invokeMethod
     */
    public static void initRequestMonitor(Method invokeMethod, String url) {
        MonitorConfig config = invokeMethod.getAnnotation(MonitorConfig.class);
        PermissionConfig pConfig = invokeMethod.getAnnotation(PermissionConfig.class);
        TokenConfig tokenConfig = invokeMethod.getAnnotation(TokenConfig.class);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        annotationConfig.setUri(url);
        if (config != null) {
            annotationConfig.setMonitorConfig(config);
        }
        if (pConfig != null) {
            annotationConfig.setPermissionConfig(pConfig);
        }
        if (tokenConfig != null) {
            annotationConfig.setTokenConfig(tokenConfig);
        }
        setAnnotationConfig(annotationConfig);
    }

    public static RequestLocal getRequestLocal() {
        return requestLocal.get();
    }

    private static void setRequestLocal(RequestLocal requestLocal1) {
        requestLocal.set(requestLocal1);
    }


    public void setLoginUser(LoginUser loginUser1) {
        loginUser.set(loginUser1);
    }

    public static void setAnnotationConfig(AnnotationConfig config) {
        annotationConfig.set(config);
    }

  /*  public static Session getSession() {
        return session.get();
    }*/

    public static AnnotationConfig getAnnotationConfig() {
        return annotationConfig.get();
    }

    /**
     * 销毁环境回收资源，刷新会话
     */
    public static void destroy() {
        try {
            if (getRequestLocal() != null && getRequestLocal().getSession() != null) {
                getRequestLocal().getSession().refresh();
            }
            annotationConfig.remove();
            requestLocal.remove();
            interceptor.remove();
            logger.info("回收线程资源成功!");
        } catch (Throwable e) {
            logger.error("清除变量失败！");
            annotationConfig.remove();
            requestLocal.remove();
            interceptor.remove();
        }
    }

    public static class RequestLocal {
        private Session session;
        private Map<String, Object> paramMap;

        public RequestLocal(HttpServletRequest request) {
            this.paramMap = initParam(request);
            initSession();
        }

        public Session getSession() {
            return this.session;
        }

        /**
         * 生成Session,根据id从缓存中获取,不存在则创建
         */
        private void initSession() {
            String uid = (String) paramMap.get("uid");
            this.session = SessionUtils.getSession(uid);

        }

        /**
         * 解析参数
         *
         * @param request
         * @return
         */
        private Map initParam(HttpServletRequest request) {
            Map<String, String[]> params = request.getParameterMap();
            Map<String, String> paramMap = new HashMap<String, String>();
            for (String key : params.keySet()) {
                String value;
                if (params.get(key) == null) {
                    value = null;
                } else {
                    value = params.get(key)[0];
                }
                paramMap.put(key, value);
            }
            return paramMap;

        }
    }

    /**
     * 拦截器信息
     */
    public static class Interceptor {
        HandlerInterceptor interceptor;
        String reason;

        public Interceptor(HandlerInterceptor interceptor, String reason) {
            this.interceptor = interceptor;
            this.reason = reason;
        }

        public HandlerInterceptor getInterceptor() {
            return interceptor;
        }

        public void setInterceptor(HandlerInterceptor interceptor) {
            this.interceptor = interceptor;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }


}
