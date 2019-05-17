package com.adundar.userservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adundar.userservice.model.Result;

@FeignClient(name = "message-service", fallback = MessageClientFallback.class)
public interface MessageClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/messages")
    Result<?> getAllMessages();

}
