package cn.swunlp.backend.base.security.util;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

/**
 * @author TangXi
 * @since 2024/2/1
 */

public class HttpClientUtils {

    private final static Logger logger = Logger.getLogger(HttpClientUtils.class.getName());

    private final static HttpClient CLIENT = HttpClient.newHttpClient();

    private HttpClientUtils() {
    }

    @SneakyThrows
    public static <R> R post(String url, Object body,Class<R> clazz) {
        // 构建请求头
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                .POST(HttpRequest.BodyPublishers.ofString(JSONUtils.toJson(body)))
                .setHeader("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return JSONUtils.toObject(response.body(), clazz);
        }
        logger.info("请求失败，结果：" + response.body());
        return null;
    }
}
