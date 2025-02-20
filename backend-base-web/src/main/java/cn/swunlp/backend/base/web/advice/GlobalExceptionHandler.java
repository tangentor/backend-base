package cn.swunlp.backend.base.web.advice;

import cn.swunlp.backend.base.base.exception.BusinessException;
import cn.swunlp.backend.base.web.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author TangXi
 */
@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public Result<?> handleRuntimeException(Exception e){
		log.warn(e.getMessage());
		e.printStackTrace();
		return Result.error(e.getMessage());
	}

	@ExceptionHandler(BusinessException.class)
	public Result<?> handleBusinessException(BusinessException e) {
		log.warn(e.getMessage());
		e.printStackTrace();
		// 创建一个标准的 Result 对象，并设置异常消息作为错误提示
		return Result.error(e.getMessage());
	}

}
