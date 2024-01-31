package cn.swunlp.backend.base.security.constant;

import lombok.Getter;

/**
 * 应用注册结果
 *
 * @author TangXi
 */
@Getter
public enum AppRegisterResult {

    /**
     * 注册成功
     */
    SUCCESS(0),

    /**
     * 注册失败
     */
    FAIL(1),

    /**
     * 应用已存在
     */
    APP_EXIST(2),

    /**
     * 应用编码不存在
     */
    APP_CODE_NOT_EXIST(3),

    /**
     * 应用编码与名称不匹配
     */
    APP_CODE_NOT_MATCH(4);

    /**
     * -- GETTER --
     *  获取枚举值
     */
    private final int value;

    AppRegisterResult(int i) {
        this.value = i;
    }

    /**
     * 获取枚举常量的名称
     */
    public String getName() {
        return name();
    }
}
