package cn.swunlp.backend.base.web.advice;

import cn.swunlp.backend.base.web.entity.Result;
import cn.swunlp.backend.base.web.util.JSONUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 功能描述：
 * 		统一响应结果处理
 * @author TangXi
 * @version 1.0
 */
@ControllerAdvice
public class JsonResultHandler implements ResponseBodyAdvice<Object> {

	private static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";

	/**
	 * 判断是否需要处理响应结果
	 *
	 * @param returnType      方法参数类型
	 * @param converterType   HttpMessageConverter 类型
	 * @return 是否需要处理响应结果
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null) {
			Object attribute = requestAttributes.getRequest().getAttribute(RESPONSE_RESULT_ANN);
			return attribute != null && "YES!".equalsIgnoreCase(attribute.toString());
		}
		return false;
	}

	/**
	 * 在响应结果写入之前进行处理
	 *
	 * @param body                   原始响应结果
	 * @param returnType             方法参数类型
	 * @param selectedContentType    选择的媒体类型
	 * @param selectedConverterType  选择的转换器类型
	 * @param request                ServerHttpRequest 对象
	 * @param response               ServerHttpResponse 对象
	 * @return 处理后的响应结果
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if (body instanceof Result) {
			return body;
		}
		if (body instanceof String) {
			return JSONUtil.toJson(Result.adjust(body));
		}
		return Result.adjust(body);
	}
}
