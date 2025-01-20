package com.xmin.lecture.restful;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.DEFAULT_CHAT_MEMORY_CONVERSATION_ID;

@RestController
@RequiredArgsConstructor
public class ChatController {

    final ChatClient chatClient;


    final VectorStore vectorStore;


    @GetMapping("/ai/chatWithRole")
    public String chatWithRole(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/ai/chatWithMemory")
    public String chatWithMemory(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return chatClient.prompt()
                // Set advisor parameters at runtime
                .advisors(advisor -> advisor.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, DEFAULT_CHAT_MEMORY_CONVERSATION_ID)
                        .param(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/ai/ragChat")
    public String ragChat(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Advisor retrievalAugmentationAdvisor = RetrievalAugmentationAdvisor.builder()
                .documentRetriever(VectorStoreDocumentRetriever.builder()
                        .similarityThreshold(0.50)
                        .vectorStore(vectorStore)
                        .build())
                .build();

        return chatClient.prompt()
                .advisors(retrievalAugmentationAdvisor)
                .user(message)
                .call()
                .content();

    }

}
