package com.demo.controller;

import com.demo.service.FeignConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignClientController {

    @Autowired
    private FeignConsumer feignConsumer;

    @GetMapping("test/hi")
    public String test(){
        return feignConsumer.test("hi feign");
    }
}
