package com.xmin.lecture.funcs;

import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class FuncRegistry {


    @Bean
    public Function<OaService.Request, OaService.Response> askForLeave() {

        return new OaService();

    }


    @Bean
    public FunctionCallback askForLeaveCallback() {
        return FunctionCallback.builder().function("askForLeave", new OaService())
                .description("当有人需要请假时，返回请假的天数")
                .inputType(OaService.Request.class)
                .build();
    }

}
