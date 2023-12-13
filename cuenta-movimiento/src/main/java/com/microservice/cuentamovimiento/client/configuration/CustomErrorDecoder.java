package com.microservice.cuentamovimiento.client.configuration;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        System.out.println("Error Response!!!");

        if (400 == response.status()) {
            System.out.println("It's a 400 Error!!!");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}