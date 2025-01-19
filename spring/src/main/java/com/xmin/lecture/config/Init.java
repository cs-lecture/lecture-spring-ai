package com.xmin.lecture.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.moonshot.MoonshotChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Init {

    final MoonshotChatModel chatModel;

//    @Bean
//    @Primary
//    public ChatClient chatClient() {
//        return ChatClient.builder(chatModel)
//                .build();
//    }
//
//
//    @Bean(name = "withRole")
//    public ChatClient chatClientWithRole() {
//        return ChatClient.builder(chatModel)
//                .defaultSystem("你的身份是美国总统特朗普，我问你任何问题的时候，你需要以特朗普的语气和知识背景来回答我")
//                .build();
//    }

    @Bean
    public InMemoryChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    public ChatClient chatClientWithMem(InMemoryChatMemory chatMemory, VectorStore vectorStore) {
        return ChatClient.builder(chatModel)
                //.defaultAdvisors(new PromptChatMemoryAdvisor(chatMemory))
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory), new QuestionAnswerAdvisor(vectorStore))
                .build();
    }


//    @Bean
//    public VectorStore vectorStore(JdbcTemplate jdbcTemplate, EmbeddingModel embeddingModel) {
//        return PgVectorStore.builder(jdbcTemplate, embeddingModel)
//                .dimensions(1536)                    // Optional: defaults to model dimensions or 1536
//                .distanceType(COSINE_DISTANCE)       // Optional: defaults to COSINE_DISTANCE
//                .indexType(HNSW)                     // Optional: defaults to HNSW
//                .initializeSchema(true)              // Optional: defaults to false
//                .schemaName("public")                // Optional: defaults to "public"
//                .vectorTableName("vector_store")     // Optional: defaults to "vector_store"
//                .maxDocumentBatchSize(10000)         // Optional: defaults to 10000
//                .build();
//    }


}
