package com.demo.message;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(value = {StreamClient.class})
public class StreamConsumer {

    @StreamListener(StreamClient.CHANNEL)
    public void receive(String message){
        System.out.println("Stream Receive:"+message);
    }
}
