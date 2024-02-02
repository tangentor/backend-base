package cn.swunlp.backend.base.security.exception;

import cn.swunlp.backend.base.base.exception.BusinessException;

/**
 * @author TangXi
 * @since 2024/2/1
 */

public class NoSuchAuthServerException extends BusinessException {
    public NoSuchAuthServerException(String msg) {
        super(msg);
    }
}
