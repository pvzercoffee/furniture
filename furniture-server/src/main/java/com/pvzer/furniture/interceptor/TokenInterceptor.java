package com.pvzer.furniture.interceptor;

import com.pvzer.furniture.utils.CurrentHolder;
import com.pvzer.furniture.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");

        //验证令牌有效性
        try{
            Claims claims = JwtUtils.parseToken(token);

            //方便获取用户id，先存在LocalThread
            setUserId(claims.get("id",Integer.class));
            return true;
        }catch(RuntimeException e){
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //请求结束后清除LocalThread
        CurrentHolder.clear();
    }

    public void setUserId(Integer userId){
        CurrentHolder.setCurrentId(userId);
    }
}
