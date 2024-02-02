package cn.swunlp.backend.base.web.constant;

import lombok.Data;

/**
 * 功能描述：
 *  鉴权结果
 * @author TangXi
 * @since 2024/2/1
 */

@Data
public class AuthResult {
    /**
     * 鉴权的结果
     * 0：失败
     * 1：成功
     */
    private int code;

    /**
     * 鉴权的结果信息
     */
    private String msg;

    /**
     * 当鉴权成功后保存的用户名
     */
    private String username;

    /**
     * 当鉴权成功后保存的昵称
     */
    private String nickname;
}
