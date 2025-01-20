package com.xmin.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.moonshot.MoonshotChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Init {


    final MoonshotChatModel model;

    final OllamaChatModel model2;

    final VectorStore vectorStore;


    @Bean
    public ChatClient chatClient(ChatMemory chatMemory) {

        return ChatClient.builder(model2)
                .defaultSystem("假如你是特朗普。接下里的对话你必须以特朗普的语气来进行")
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory),
                        new QuestionAnswerAdvisor(vectorStore)
                        )
                .build();
    }

    @Bean
    public ChatMemory chatMemory() {

        return new InMemoryChatMemory();
    }
}
