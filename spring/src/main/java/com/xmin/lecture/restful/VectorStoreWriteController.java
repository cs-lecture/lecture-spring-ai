package com.xmin.lecture.restful;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class VectorStoreWriteController {

    final VectorStore vectorStore;

    @GetMapping("/vec/write")
    public String chatWithRole() {
        StringBuilder text = new StringBuilder();
        ClassLoader classLoader = VectorStoreWriteController.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("nocode.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vectorStore.write(Stream.of(text.toString().split("。")).map(Document::new).toList());
        return "success";
    }

}
