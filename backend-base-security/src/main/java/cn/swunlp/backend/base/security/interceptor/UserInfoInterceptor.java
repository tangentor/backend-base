package cn.swunlp.backend.base.security.interceptor;

import cn.swunlp.backend.base.security.util.UserInfoUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 访问控制拦截器
 * @author TangXi
 * @since 2024/2/2
 */

@RequiredArgsConstructor
@Component
public class UserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull Object handler) throws Exception {
        // 获取请求头中的用户信息，约定以GATEWAY_PASS_USERINFO为请求头“#”分隔
        String headerName = "GATEWAY_PASS_USERINFO";
        String userInfo = request.getHeader(headerName);
        if (!StringUtils.hasText(userInfo)) {
//            response.setHeader("Content-Type", "text/plain;charset=UTF-8");
//            response.getWriter().write("用户信息为空");
//            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return true;
        }
        String[] info = userInfo.split("#");
        if (info.length != 2) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.setHeader("Content-Type", "text/plain;charset=UTF-8");
            response.getWriter().write("用户信息格式错误");
            return false;
        }
        UserInfoUtils.setUserInfo(info[0], info[1]);
        return true;
    }
}
