package com.demo.service;

import org.springframework.stereotype.Component;

@Component
public class FallbackService implements FeignConsumer{

    @Override
    public String test(String message) {
        return "test fallback";
    }
}
