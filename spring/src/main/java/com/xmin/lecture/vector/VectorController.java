package com.xmin.lecture.vector;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class VectorController {

    final VectorStore vectorStore;


    @GetMapping("/ai/load")
    public String loadVector() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        ClassLoader classLoader = VectorController.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("nocode.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {

                stringBuilder.append(line);
            }

        }
        vectorStore.write(Stream.of(stringBuilder.toString().split("。")).map(Document::new).collect(Collectors.toList()));

        return "success";

    }

}
