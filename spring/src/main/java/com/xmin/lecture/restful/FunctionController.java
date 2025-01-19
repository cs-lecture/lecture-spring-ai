package com.xmin.lecture.restful;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FunctionController {

    final ChatClient chatClient;


    @GetMapping("/func/askForLeave")
    public String ragChat(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return chatClient.prompt(message)
                .functions("askForLeave")
                .call()
                .content();
    }
}
