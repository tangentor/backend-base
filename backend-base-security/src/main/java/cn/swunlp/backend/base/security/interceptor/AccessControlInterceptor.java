package cn.swunlp.backend.base.security.interceptor;

import cn.swunlp.backend.base.security.access.AccessControlValueHolder;
import cn.swunlp.backend.base.security.config.AccessControlProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 访问控制拦截器
 * @author TangXi
 * @since 2024/2/2
 */

@RequiredArgsConstructor
public class AccessControlInterceptor implements HandlerInterceptor {


    private final AccessControlProperties accessControl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断请求的方法是否符合要求
        if(!accessControl.isEnable()){
            return true;
        }
        // 检查请求头是否符合要求
        String value = AccessControlValueHolder.getValue();
        String lastValue = AccessControlValueHolder.getLastValue();
        String checkValue = request.getHeader(accessControl.getHeader());
        if(!StringUtils.hasText(value) || !value.equals(checkValue) || !checkValue.equals(lastValue)){
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied, The Application Enable Access Control!");
            return false;
        }
        return true;
    }
}
