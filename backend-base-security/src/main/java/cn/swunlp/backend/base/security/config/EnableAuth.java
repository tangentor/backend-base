package cn.swunlp.backend.base.security.config;

import cn.swunlp.backend.base.security.annotation.EnableAuthConfiguration;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Objects;

/**
 * @author TangXi
 * @since 2024/2/5
 */

public class EnableAuth implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> beansWithAnnotation = Objects.requireNonNull(context.getBeanFactory()).getBeansWithAnnotation(EnableAuthConfiguration.class);
//        System.out.println(beansWithAnnotation);
        return !beansWithAnnotation.isEmpty();
    }
}
