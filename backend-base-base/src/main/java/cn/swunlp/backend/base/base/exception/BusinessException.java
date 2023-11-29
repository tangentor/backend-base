package cn.swunlp.backend.base.base.exception;

/**
 * 功能描述:
 *      业务类操作异常
 * @author TangXi
 * @version 1.0
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
