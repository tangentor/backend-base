package cn.swunlp.backend.base.security.client;

import cn.swunlp.backend.base.security.constant.AppRegisterResult;
import cn.swunlp.backend.base.security.entity.ApplicationPermission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author TangXi
 * @since 2024/1/29
 */
//@FeignClient(value = "AuthService",path = "/auth")
public interface AuthServiceClient {

    /**
     * 注册应用
     * @param applicationPermission 应用权限
     * @return 注册结果
     */
//    @PostMapping("/register")
    AppRegisterResult doRegister(@RequestBody ApplicationPermission applicationPermission);
}
