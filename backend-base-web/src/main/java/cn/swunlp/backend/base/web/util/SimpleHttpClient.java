package cn.swunlp.backend.base.web.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 简单的HttpClient工具类，基于Java 17中的java.net.http包。
 * 支持发起GET和POST请求，并提供接口以及默认的JSON解析处理方式。
 * @author Tangxi
 * @version 1.0
 */
public class SimpleHttpClient {

    private final HttpClient httpClient;

    /**
     * 构造方法，初始化HttpClient。
     */
    public SimpleHttpClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * 发起GET请求并异步处理结果。
     *
     * @param url 请求的URL
     * @return 表示异步操作结果的CompletableFuture
     */
    public CompletableFuture<String> get(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return sendRequest(request);
    }

    /**
     * 发起POST请求并异步处理结果。
     *
     * @param url     请求的URL
     * @param body    POST请求的请求体
     * @param headers 请求的头部信息
     * @return 表示异步操作结果的CompletableFuture
     */
    public CompletableFuture<String> post(String url, String body, Map<String, String> headers) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers(headers.entrySet().stream()
                        .flatMap(entry -> Map.of(entry.getKey(), entry.getValue()).entrySet().stream())
                        .toArray(String[]::new))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return sendRequest(request);
    }

    /**
     * 发送请求并处理结果。
     *
     * @param request HttpRequest对象
     * @return 表示异步操作结果的CompletableFuture
     */
    private CompletableFuture<String> sendRequest(HttpRequest request) {

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    /**
     * 自定义处理方式的接口。
     */
    public interface ResponseHandler {
        /**
         * 处理HTTP响应的方法。
         *
         * @param response HTTP响应的字符串形式
         */
        void handleResponse(String response);
    }

    /**
     * 默认以JSON方式解析的处理方式。
     */
    public static class JsonResponseHandler implements ResponseHandler {
        @Override
        public void handleResponse(String response) {
            // 在这里实现JSON解析逻辑，这里只是简单打印
            System.out.println("JSON Response: " + response);
        }
    }

    public static void main(String[] args) {
        SimpleHttpClient client = new SimpleHttpClient();
        CompletableFuture<Void> voidCompletableFuture = client.get("http://localhost:8080/").thenAccept(System.out::println);
//        client.post("https://www.baidu.com", "body", Map.of("Content-Type", "application/json"))
//                .thenAccept(System.out::println);
        client.get("https://www.baidu.com").thenAccept(new JsonResponseHandler()::handleResponse);
    }
}
