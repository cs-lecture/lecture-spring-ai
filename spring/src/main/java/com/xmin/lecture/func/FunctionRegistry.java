package com.xmin.lecture.func;

import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FunctionRegistry {


    @Bean
    public FunctionCallback askForLeaveCallBack() {

        return FunctionCallback.builder()
                .function("askForLeave", new OaService())
                .description("当有人请假时，返回请假天数")
                .inputType(OaService.Request.class)
                .build();
    }

}
