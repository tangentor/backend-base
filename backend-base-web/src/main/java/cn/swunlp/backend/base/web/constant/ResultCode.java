package cn.swunlp.backend.base.web.constant;

/**
 * 功能描述：
 *
 * @author TangXi
 * @date 2023/11/27 15:53
 */
public class ResultCode {
    // 成功状态码
    public static final int SUCCESS = 200;

    // 客户端错误状态码
    public static final int BAD_REQUEST = 400; // 请求参数有误
    public static final int UNAUTHORIZED = 401; // 未认证
    public static final int FORBIDDEN = 403; // 无权访问
    public static final int NOT_FOUND = 404; // 资源不存在
    public static final int METHOD_NOT_ALLOWED = 405; // 请求方法不允许
    public static final int CONFLICT = 409; // 资源冲突
    public static final int UN_PROCESSABLE_ENTITY = 422; // 请求参数验证失败

    // 服务端错误状态码
    public static final int INTERNAL_SERVER_ERROR = 500; // 服务器内部错误
    public static final int SERVICE_UNAVAILABLE = 503; // 服务不可用

    // 微服务间通信状态码
    public static final int MICROSERVICE_UNAVAILABLE = 600; // 微服务不可用
    public static final int MICROSERVICE_TIMEOUT = 601; // 微服务调用超时
    public static final int MICROSERVICE_EXCEPTION = 602; // 微服务调用异常

    // 数据库操作状态码
    public static final int DATABASE_ERROR = 700; // 数据库操作错误
    public static final int DUPLICATE_KEY = 701; // 唯一键冲突

    // 消息队列状态码
    public static final int MESSAGE_QUEUE_ERROR = 800; // 消息队列操作错误
    public static final int MESSAGE_CONSUME_ERROR = 801; // 消息消费错误

    // 网关状态码
    public static final int GATEWAY_TIMEOUT = 900; // 网关超时
    public static final int GATEWAY_ERROR = 901; // 网关错误

    // 业务处理状态码
    public static final int BUSINESS_ERROR = 1000; // 业务处理错误
    public static final int BUSINESS_VALIDATION_ERROR = 1001; // 业务参数验证失败
    public static final int BUSINESS_NOT_FOUND = 1002; // 业务资源不存在
    public static final int BUSINESS_CONFLICT = 1003; // 业务资源冲突
    public static final int BUSINESS_LIMIT_EXCEEDED = 1004; // 业务限额超出
    public static final int BUSINESS_OPERATION_NOT_ALLOWED = 1005; // 不允许的业务操作
    public static final int BUSINESS_DEPENDENCY_FAILURE = 1006; // 业务依赖失败
    public static final int BUSINESS_DATA_INTEGRITY_ERROR = 1007; // 业务数据完整性错误
}

