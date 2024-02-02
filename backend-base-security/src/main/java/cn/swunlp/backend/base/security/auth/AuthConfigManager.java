package cn.swunlp.backend.base.security.auth;

import cn.swunlp.backend.base.base.util.ReflectUtils;
import cn.swunlp.backend.base.security.annotation.EnableAuthConfiguration;
import cn.swunlp.backend.base.security.annotation.NotRequireAuth;
import cn.swunlp.backend.base.security.annotation.RequireAuth;
import cn.swunlp.backend.base.security.config.AppConfigProperties;
import cn.swunlp.backend.base.security.constant.AppAuthenticate;
import cn.swunlp.backend.base.security.entity.ApplicationPermission;
import cn.swunlp.backend.base.security.entity.MethodPermission;
import cn.swunlp.backend.base.security.exception.AppConfigCheckException;
import cn.swunlp.backend.base.security.strategy.AuthStrategy;
import cn.swunlp.backend.base.security.util.IgnorePathLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;

/**
 * 动态获取所有接口的权限配置
 *
 * @author TangXi
 * @since 2024/1/29
 */
@Component
@RequiredArgsConstructor
public class AuthConfigManager implements ApplicationRunner {

    private final Logger logger = Logger.getLogger(AuthConfigManager.class.getName());

    private final ApplicationContext applicationContext;

    private final AppConfigProperties appConfigProperties;

    /**
     * 应用权限信息
     */
    private final ApplicationPermission applicationPermission = new ApplicationPermission();

    /**
     * 方法权限信息
     */
    private final List<MethodPermission> methodPermissions = new ArrayList<>();

    /**
     * 应用前缀
     */
    @Value("${server.servlet.context-path:/}")
    private String prefix;

    private boolean enableDebug = false;


    private final AuthConfigRegister authConfigRegister;

    @Override
    public void run(ApplicationArguments args) {
        if (!checkIsEnable()) {
            return;
        }
        logger.info("检查应用配置参数...");
        checkApplicationConfig();
        // 找到所有的controller层接口，也就是被@controller注解的类
        logger.info("开始扫描权限配置...");
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        logger.info("开始处理权限配置信息...");
        loadPermission(requestMappingHandlerMapping);
        logger.info("权限配置信息处理完成...");
        logger.info("设置应用基本信息...");
        setBaseApplicationConfig();
        logger.info("应用基本信息设置完成...");
        logger.info("开始注册应用权限信息...");
        // 注册应用信息
        doRegister(applicationPermission);
        logger.info("应用权限信息注册完成...");
    }

    private void setBaseApplicationConfig() {
        applicationPermission.setApplicationName(appConfigProperties.getName());
        applicationPermission.setApplicationCode(appConfigProperties.getCode());
        applicationPermission.setPrefix(prefix);
    }

    private boolean checkIsEnable() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Configuration.class);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            // 除了自己定义的配置类，还有一些spring自带的配置类，这里过滤掉
            if (entry.getKey().contains(".")) {
                continue;
            }
            EnableAuthConfiguration annotation = ReflectUtils.findAnnotation(entry.getValue(), EnableAuthConfiguration.class);
            if (annotation != null) {
                enableDebug = annotation.debug();
                return true;
            }
        }
        return false;
    }

    private void doRegister(ApplicationPermission applicationPermission) {
        if(enableDebug){
            printRegisterInfo(applicationPermission);
        }
        authConfigRegister.doRegister(applicationPermission);
    }

    private void printRegisterInfo(ApplicationPermission applicationPermission) {
        logger.info("该应用的应用权限如下：");
        logger.info("应用名称：" + applicationPermission.getApplicationName());
        logger.info("应用代码：" + applicationPermission.getApplicationCode());
        logger.info("应用前缀：" + applicationPermission.getPrefix());
        logger.info("应用权限信息：");
        applicationPermission.getMethodPermissions().forEach(System.out::println);
    }

    /**
     * 解析方法权限
     */
    private void loadPermission(RequestMappingHandlerMapping handlerMapping) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        //遍历映射关系
        handlerMethods.forEach(this::loadMapperInfo);
        logger.info(handlerMethods.toString());
        //将权限信息添加到应用权限中
        applicationPermission.setMethodPermissions(methodPermissions);
    }

    private void loadMapperInfo(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
        Set<String> ignorePaths = IgnorePathLoader.load();
        Set<String> patternValues = requestMappingInfo.getPatternValues();
        Set<String> paths = new HashSet<>(requestMappingInfo.getDirectPaths());
        paths.addAll(patternValues);
        // 将两种URL路径合并
        // 获取请求方法与请求路径
        for (String directPath : paths) {
            Set<RequestMethod> requestMethods = requestMappingInfo.getMethodsCondition().getMethods();
            // 使用的是RequestMapping注解，没有指定请求方法，那么默认支持所有请求方法
            if (requestMethods.isEmpty()) {
                requestMethods = setAllRequestMethod();
            }
            for (RequestMethod requestMethod : requestMethods) {
                // 忽略的路径
                if (ignorePaths.contains(directPath)) {
                    continue;
                }
                Method method = handlerMethod.getMethod();
                MethodPermission methodPermission = new MethodPermission();
                methodPermission.setUri(directPath);
                methodPermission.setRequestMethod(requestMethod.name());
                // 方法的全路径名
                methodPermission.setMethodName(method.getName());
//                methodPermission.setHandler(handlerMethod);
                this.parsePermission(method, methodPermission);
                methodPermissions.add(methodPermission);
            }
        }
    }

    private Set<RequestMethod> setAllRequestMethod() {
        return Set.of(RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE);
    }

    private void parsePermission(Method method, MethodPermission methodPermission) {
        // 是否备@NotRequireAuth注解标注
        if (ReflectUtils.isAnnotated(method, NotRequireAuth.class)) {
            // 设置权限为不需要认证
            methodPermission.setPermissions(new String[]{AppAuthenticate.NOT_REQUIRED});
            return;
        }
        // 解析Auth注解信息
        RequireAuth requireAuth = ReflectUtils.findAnnotation(method, RequireAuth.class);
        if (requireAuth == null) {
            // 没有找到@RequireAuth注解，统一执行默认策略
            useDefaultStrategy(methodPermission);
        } else {
            // 使用注解信息设置权限
            setMethodPermission(methodPermission, requireAuth);
        }
    }

    private void useDefaultStrategy(MethodPermission methodPermission) {
        AuthStrategy strategy = applicationContext.getBean(AuthStrategy.class);
        methodPermission.setPermissions(strategy.permissions());
        methodPermission.setRoles(strategy.roles());
        methodPermission.setRequireAll(strategy.requireAll());
        methodPermission.setAllowedOrigins(strategy.allowedOrigins());
        methodPermission.setInternalSource(strategy.isInternalSource());
    }

    private void setMethodPermission(MethodPermission methodPermission, RequireAuth requireAuth) {
        methodPermission.setPermissions(requireAuth.permissions());
        methodPermission.setRoles(requireAuth.roles());
        methodPermission.setRequireAll(requireAuth.requireAll());
        methodPermission.setAllowedOrigins(requireAuth.allowedOrigins());
        methodPermission.setInternalSource(requireAuth.isInternalSource());
    }


    /**
     * 这里只是进行存在性检查，没有进行有效性验证
     * 有效性验证在请求到AuthService的时候以返回状态进行区分
     */
    private void checkApplicationConfig() {
        if (appConfigProperties.getName() == null || appConfigProperties.getName().isEmpty()) {
            throw new AppConfigCheckException("应用名称不能为空");
        }
        if (appConfigProperties.getCode() == null || appConfigProperties.getCode().isEmpty()) {
            throw new AppConfigCheckException("应用代码不能为空");
        }
    }
}
