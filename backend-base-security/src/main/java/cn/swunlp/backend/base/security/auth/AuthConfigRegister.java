package cn.swunlp.backend.base.security.auth;

import cn.swunlp.backend.base.security.constant.AppRegisterResult;
import cn.swunlp.backend.base.security.entity.ApplicationPermission;
import cn.swunlp.backend.base.security.exception.AppRegisterException;
import cn.swunlp.backend.base.security.loader.AuthServerLoader;
import cn.swunlp.backend.base.security.util.HttpClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * 权限配置注册器
 * @author TangXi
 * @since 2024/1/30
 */

@Component
@RequiredArgsConstructor
public class AuthConfigRegister {

    private final Logger logger = Logger.getLogger(AuthConfigRegister.class.getName());

    private final AuthServerLoader authServerLoader;

    private String requestUrl = "/auth/register";


    public void doRegister(ApplicationPermission applicationPermission) {
        String authServer = authServerLoader.loadAvailable();
        requestUrl = authServer + requestUrl;
        // 开始注册
        AppRegisterResult result = HttpClientUtils.post(requestUrl, applicationPermission, AppRegisterResult.class);
        if(result == null){
            throw new AppRegisterException("请求失败");
        }
        if(!AppRegisterResult.SUCCESS.equals(result)){
            throw new AppRegisterException("注册失败，返回结果：" + result.getName() + "，请检查应用信息是否正确");
        }
        logger.info("注册成功");
    }

}
