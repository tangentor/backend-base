package cn.swunlp.backend.base.web.interceptor;

import cn.swunlp.backend.base.web.annotation.JsonResult;
import cn.swunlp.backend.base.web.util.ReflectUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 功能描述
 * 		结果处理拦截器
 * @author TangXi
 */
public class JsonResultInterceptor implements HandlerInterceptor {

	// 标记
	private static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 判断请求的方法是否符合要求
		if(ReflectUtils.isAnnotated(handler, JsonResult.class)){
			//设置的值可以进行约定
			request.setAttribute(RESPONSE_RESULT_ANN,"YES!");
		}
		return true;
	}
}
