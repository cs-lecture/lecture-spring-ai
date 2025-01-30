package com.xmin.lecture.mutlemodal;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageAPI {

    final OllamaChatModel model;

    @GetMapping("/ai/chatWithPic")
    public String chatWithPic() {
        ClassPathResource imageData = new ClassPathResource("/cat.png");
        Message userMessage = new UserMessage("请用中文描述一下这张图片里面有什么东西？",
                List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageData)));
        return model.call(new Prompt(List.of(userMessage),
                        ChatOptions.builder()
                                .model(OllamaModel.LLAVA.getName()).build()))
                .getResult().getOutput().getText();
    }
}
