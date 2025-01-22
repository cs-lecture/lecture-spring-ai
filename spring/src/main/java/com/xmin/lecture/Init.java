package com.xmin.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.moonshot.MoonshotChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Init {

    final MoonshotChatModel chatModel;

    @Bean
    public ChatClient chatClient(ChatMemory chatMemory, VectorStore vectorStore) {

        return ChatClient.builder(chatModel)
                .defaultSystem("假设你是特朗普，接下来的回答你都需要按照特朗普语气来进行")
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory), new QuestionAnswerAdvisor(vectorStore))
                .build();

    }

    @Bean
    public ChatMemory chatMemory() {

        return new InMemoryChatMemory();
    }

}
