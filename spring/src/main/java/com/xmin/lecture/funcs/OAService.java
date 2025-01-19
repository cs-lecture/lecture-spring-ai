package com.xmin.lecture.funcs;

import java.util.function.Function;

public class OAService implements Function<OAService.Request, OAService.Response> {

    @Override
    public Response apply(Request request) {
        System.err.printf("%s is taken off%n", request.who);
        return new Response(10);
    }

    public record Request(String who){

    }

    public record Response(int days){

    }

}
