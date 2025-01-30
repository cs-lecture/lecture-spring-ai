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
import java.util.Arrays;

@RequiredArgsConstructor
@RestController
public class VectorAPI {

    final VectorStore store;

    @GetMapping("/vec/write")
    public String write() throws IOException {


        StringBuilder text = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("nocode.txt");


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        }

        store.write(Arrays.stream(text.toString().split("。")).map(Document::new).toList());
        return "success";

    }

}
