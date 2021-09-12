package com.demo.message;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {
    String CHANNEL = "my-channel";

    @Input(StreamClient.CHANNEL)
    SubscribableChannel input();

    @Output(StreamClient.CHANNEL)
    MessageChannel output();
}
