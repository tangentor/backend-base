package cn.swunlp.backend.base.security.loader;

/**
 * @author TangXi
 * @since 2024/2/1
 */
public interface AuthServerLoader {

    /**
     * 获取可用的认证服务器地址
     * @return 以http://开头的认证服务器地址
     */
    String loadAvailable();
}
