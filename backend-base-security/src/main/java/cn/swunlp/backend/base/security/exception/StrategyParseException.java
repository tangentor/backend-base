package cn.swunlp.backend.base.security.exception;

import cn.swunlp.backend.base.base.exception.BusinessException;

/**
 * @author TangXi
 * @since 2024/1/29
 */

public class StrategyParseException extends BusinessException {
    public StrategyParseException(String msg) {
        super(msg);
    }
}
