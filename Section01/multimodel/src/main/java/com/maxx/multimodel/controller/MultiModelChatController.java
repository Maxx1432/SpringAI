package com.maxx.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MultiModelChatController {

    private final ChatClient opneAIChatClient;
    private final ChatClient ollamaChatClient;

    public MultiModelChatController(@Qualifier("openAIChatClient") ChatClient opneAIChatClient, @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.opneAIChatClient = opneAIChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    @GetMapping("/openai/chat")
    public String openAIChat(@RequestParam("message") String message) {
        return opneAIChatClient.prompt(message).call().content();
    }

    @GetMapping("/ollama/chat")
    public String ollamaChat(@RequestParam("message") String message) {
        return ollamaChatClient.prompt(message).call().content();
    }
}
