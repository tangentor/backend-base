package cn.swunlp.backend.base.security.auth;

import cn.swunlp.backend.base.security.access.AccessControlValueHolder;
import cn.swunlp.backend.base.security.config.AccessControlProperties;
import cn.swunlp.backend.base.security.constant.AppRegisterResult;
import cn.swunlp.backend.base.security.entity.AppAccessControl;
import cn.swunlp.backend.base.security.entity.ApplicationPermission;
import cn.swunlp.backend.base.security.exception.AppRegisterException;
import cn.swunlp.backend.base.security.loader.AuthServerLoader;
import cn.swunlp.backend.base.security.util.HttpClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
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

    private final AccessControlProperties accessControlProperties;

    private ApplicationPermission applicationPermission;

    private boolean enableDebug = false;


    public void doRegister(ApplicationPermission applicationPermission) {
        String uri = "/auth/register";
        String requestUrl = authServerLoader.loadAvailable() + uri;
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

    public void doRegister(ApplicationPermission applicationPermission,boolean enableDebug) {
        if (this.applicationPermission == null) {
            this.applicationPermission = applicationPermission;
            this.enableDebug = enableDebug;
        }
        setRandomAccessValue();
        if(enableDebug){
            printRegisterInfo(applicationPermission);
        }
        doRegister(applicationPermission);
    }

    private void printRegisterInfo(ApplicationPermission applicationPermission) {
        logger.info("该应用的应用权限如下：");
        logger.info("应用名称：" + applicationPermission.getApplicationName());
        logger.info("应用代码：" + applicationPermission.getApplicationCode());
        logger.info("应用前缀：" + applicationPermission.getPrefix());
        logger.info("应用访问控制：" + applicationPermission.getAppAccessControl());
        logger.info("应用权限信息：");
        applicationPermission.getMethodPermissions().forEach(System.out::println);
    }

    /**
     * 心跳机制
     * 20秒心跳一次
     * 权限服务60秒未收到心跳则认为服务已下线
     */
    @Scheduled(fixedRate = 20000,initialDelay = 10000)
    void doHeartbeat() {
        logger.info("开始发送心跳包");
        doRegister(applicationPermission,enableDebug);
    }

    /**
     * 设置随机的应用访问控制值
     */
    private void setRandomAccessValue() {
        // 如果访问控制未启用，则直接返回
        if(!accessControlProperties.isEnable()){
            return;
        }
        // 获取应用权限中的应用访问控制对象
        AppAccessControl appAccessControl = applicationPermission.getAppAccessControl();
        // 如果应用访问控制对象不为空且有效值不为空，则直接返回
        if(appAccessControl != null && accessControlProperties.getValidValue() != null){
            return;
        }
        // 创建新的应用访问控制对象
        appAccessControl = new AppAccessControl();
        // 设置访问控制对象的启用状态为true
        appAccessControl.setEnable(true);
        // 设置访问控制对象的Header
        appAccessControl.setHeader(accessControlProperties.getHeader());
        // 如果有效值不为空，则设置访问控制对象的有效值
        if(accessControlProperties.getValidValue() != null){
            appAccessControl.setValidValue(accessControlProperties.getValidValue());
        } else {
            // 否则，生成一个随机的UUID作为有效值
            appAccessControl.setValidValue(UUID.randomUUID().toString().replaceAll("-", "").substring(0,10).toUpperCase());
        }
        // 将访问控制对象的有效值设置到访问控制值持有者中
        AccessControlValueHolder.setValue(appAccessControl.getValidValue());
        // 将应用访问控制对象设置到应用权限中
        applicationPermission.setAppAccessControl(appAccessControl);
    }

}
