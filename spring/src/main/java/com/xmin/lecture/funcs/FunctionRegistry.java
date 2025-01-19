package com.xmin.lecture.funcs;

import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class FunctionRegistry {

    @Bean
    public Function<OAService.Request, OAService.Response> askForLeave() { // bean name as function name
        return new OAService();
    }

    @Bean
    public FunctionCallback askForLeaveCallback() {
        return FunctionCallback.builder()
                .function("askForLeave", new OAService())
                .description("当有人需要请假时，返回请假的天数")
                .inputType(OAService.Request.class)
                .build();
    }

}
