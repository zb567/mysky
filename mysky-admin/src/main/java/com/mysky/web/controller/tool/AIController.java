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
} 