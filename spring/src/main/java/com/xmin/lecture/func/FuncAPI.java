package com.xmin.lecture.func;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FuncAPI {

    final ChatClient chatClient;

    @GetMapping("/ai/funcCall")
    public String funcCall(@RequestParam(value = "message") String message){

        return chatClient.prompt(message)
                .functions("askForLeave")
                .call().content();

    }
}
