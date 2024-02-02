package cn.swunlp.backend.base.security.loader;

import cn.swunlp.backend.base.security.exception.NoSuchAuthServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TangXi
 * @since 2024/2/1
 */
@RequiredArgsConstructor
public class NacosAuthServerLoader implements AuthServerLoader{


    private final DiscoveryClient discoveryClient;

    @Override
    public String loadAvailable() {
        String authServerName = getServiceName();
        List<ServiceInstance> instances = discoveryClient.getInstances(authServerName);
        if (instances.isEmpty()) {
            throw new NoSuchAuthServerException("没有可用的认证服务器");
        }
        //随机选择一个认证服务器
        ServiceInstance instance = instances.get((int) (Math.random() * instances.size()));
        //返回认证服务器地址
        return String.format("http://%s:%s", instance.getHost(), instance.getPort());
    }

    public String getServiceName() {
        return "authService";
    }
}
