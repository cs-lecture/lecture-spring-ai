package com.xmin.lecture.funcs;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FuncController {


    final ChatClient chatClient;

    @GetMapping("/ai/func")
    public String chat(@RequestParam(value = "message") String message) {
        return chatClient.prompt(message)
                .functions("askForLeave")
                .call()
                .content();
    }

}
