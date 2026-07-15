package com.hmall.common.interceptors;


import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取用户信息
        String userInfo = request.getHeader("user-info");
        // 判断是否获取了用户，如果有，存入ThreadLocal中
        if(StrUtil.isNotBlank(userInfo)) {
            UserContext.setUser(Long.parseLong(userInfo));
        }
        // 如果没有，直接放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除ThreadLocal中的用户信息
        UserContext.removeUser();
    }
}
