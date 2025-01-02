package com.mysky.web.controller.tool;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mysky.common.core.domain.AjaxResult;

import java.util.Map;

@RestController
@RequestMapping("/tool/ai")
public class AIController {
    
    @PostMapping("/chat")
    public AjaxResult chat(@RequestBody Map<String, String> params) {
        try {
            String systemPrompt = params.get("systemPrompt");
            String userMessage = params.get("userMessage");
            
            if (systemPrompt == null || userMessage == null) {
                return AjaxResult.error("系统提示词和用户消息不能为空");
            }
            
            String response = AIClient.getInstance().chat(systemPrompt, userMessage);
            return AjaxResult.success("请求成功", response);
        } catch (Exception e) {
            return AjaxResult.error("AI 请求失败: " + e.getMessage());
        }
    }

    @PostMapping("/simpleChat")
    public AjaxResult simpleChat(@RequestBody Map<String, String> params) {
        try {
            String userMessage = params.get("userMessage");

            if (userMessage == null) {
                return AjaxResult.error("用户消息不能为空");
            }

            String systemPrompt = "你是一个专业的旅游路线规划助手，请根据用户输入的内容，总结出详细的旅游路线规划。包括路线名称、起点城市名称、起点景点名称、终点城市名称、终点景点名称、行程安排等信息。以json形式返回数据，生成标准的json格式数据不允许有其他内容。格式如下：{\n  \"tour_name\": \"Sakamoto Ryoma " +
                    "Historical Tour\",\n  \"departure_city\": \"Kochi, Japan\",\n  \"departure_spot\": \"Sakamoto Ryoma Memorial Museum\",\n  \"destination_city\": \"Kochi, Japan\",\n  " +
                    "\"destination_spot\": \"Ryoma Birthplace Sites\",\n  \"itinerary\": [\n    {\n      \"time\": \"8:30 AM - 10:30 AM\",\n      \"activity\": \"Visit Sakamoto Ryoma Memorial " +
                    "Museum\",\n      \"details\": [\n        \"Renovated facility with interactive exhibits (2018)\",\n        \"Comprehensive exhibits about Ryoma's life and influence\",\n       " +
                    " \"Museum shop for traditional crafts and memorabilia\"\n      ]\n    },\n    {\n      \"time\": \"10:30 AM - 11:30 AM\",\n      \"activity\": \"Katsurahama Ryoma Statue\",\n  " +
                    "    \"details\": [\n        \"Iconic statue overlooking the Pacific Ocean\",\n        \"Photo opportunities with ocean views\",\n        \"Walking along Katsurahama beach\"\n  " +
                    "    ]\n    },\n    {\n      \"time\": \"1:00 PM - 3:30 PM\",\n      \"activity\": \"Explore Kochi Castle & Historical Museum\",\n      \"details\": [\n        \"Explore the " +
                    "historic castle complex\",\n        \"Visit the modern Historical Museum (opened 2017)\",\n        \"Learn about the role of Yamanouchi Toyoshige\",\n        \"Panoramic city " +
                    "views from castle tower\"\n      ]\n    },\n    {\n      \"time\": \"3:30 PM - 4:30 PM\",\n      \"activity\": \"Ryoma Birthplace Sites\",\n      \"details\": [\n        " +
                    "\"Historical stone monument at Kamimachi 2-chome\",\n        \"Visit Kochi City Ryoma Birthplace Memorial Museum\",\n        \"Local historical neighborhood exploration\"\n    " +
                    "  ]\n    }\n  ],\n  \"package_price\": \"$200 per person\"\n}";
            userMessage = userMessage + "内容请用中文";
            String response = AIClient.getInstance().chat(systemPrompt, userMessage);
            return AjaxResult.success("请求成功", response);
        } catch (Exception e) {
            return AjaxResult.error("AI 请求失败: " + e.getMessage());
        }
    }

    
} 