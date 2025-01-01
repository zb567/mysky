// -*- coding: utf-8 -*-
package com.mysky.web.controller.tool;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class AIClient {
    private static volatile AIClient instance;
    private final CloseableHttpClient httpClient;
    private final String apiUrl;
    
    private AIClient() {
        this.httpClient = HttpClients.createDefault();
        this.apiUrl = "https://ai-proxy-a9ls.onrender.com/api/chat";
    }
    
    public static AIClient getInstance() {
        if (instance == null) {
            synchronized (AIClient.class) {
                if (instance == null) {
                    instance = new AIClient();
                }
            }
        }
        return instance;
    }
    
    /**
     * 发送聊天请求
     * @param systemPrompt 系统提示词
     * @param userMessage 用户消息
     * @return API响应内容
     * @throws Exception 如果请求失败
     */
    public String chat(String systemPrompt, String userMessage) throws Exception {
        String requestBody = "{"
            + "    \"model\": \"gpt-4o\","
            + "    \"messages\": ["
            + "        {\"role\": \"system\", \"content\": \"" + escapeJson(systemPrompt) + "\"},"
            + "        {\"role\": \"user\", \"content\": \"" + escapeJson(userMessage) + "\"}"
            + "    ]"
            + "}";

        HttpPost request = new HttpPost(apiUrl);
        request.setHeader("Content-Type", "application/json; charset=utf-8");
        request.setEntity(new StringEntity(requestBody, "UTF-8"));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("API request failed with status code: " + statusCode);
            }
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
    }
    
    /**
     * 转义JSON字符串
     */
    private String escapeJson(String input) {
        return input.replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
} 