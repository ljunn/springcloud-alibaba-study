package com.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-provider",fallback = FallbackService.class)
public interface FeignConsumer {
    @GetMapping(value = "/test/{message}")
    String test(@PathVariable("message" )String message);
}
