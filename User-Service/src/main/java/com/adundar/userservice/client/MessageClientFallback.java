package com.adundar.userservice.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.adundar.userservice.model.Result;

@Component
public class MessageClientFallback implements MessageClient {

    @Override
    public Result<?> getAllMessages() {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE);
    }

}
