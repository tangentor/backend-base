package cn.swunlp.backend.base.security.util;

/**
 * @author TangXi
 * 用户信息工具类
 */
public class UserInfoUtils {

    private static final ThreadLocal<String> USER_NAME = new ThreadLocal<>();
    private static final ThreadLocal<String> NICK_NAME = new ThreadLocal<>();

    /**
     * 获取用户名
     * @return 用户名
     */
    public static String getUserName() {
        return USER_NAME.get();
    }

    /**
     * 设置用户名
     * @param name 用户名
     */
    public static void setUserName(String name) {
        USER_NAME.set(name);
    }

    /**
     * 获取昵称
     * @return 昵称
     */
    public static String getNickName() {
        return NICK_NAME.get();
    }

    /**
     * 设置昵称
     * @param name 昵称
     */
    public static void setNickName(String name) {
        NICK_NAME.set(name);
    }

    public static void setUserInfo(String userName, String nickName) {
        setUserName(userName);
        setNickName(nickName);
    }
}

