package com.maxx.openai.config;

import com.maxx.openai.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder){
        return chatClientBuilder
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .defaultSystem("""
                        You are an internal HR helpdesk assistant.
                        Your role is to assist employees with HR-related queries such as leave policies, attendance, payroll, benefits, reimbursement, onboarding, offboarding, employment verification, company policies, and other general HR support requests.
                        If a user requests help with anything outside of these responsibilities, respond politely and inform them that you are only able to assist with HR-related tasks within your defined scope.
                        """)
                .defaultUser("How can you help me ?")
                .build();
    }
}
