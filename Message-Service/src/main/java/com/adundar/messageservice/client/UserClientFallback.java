package com.adundar.messageservice.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.adundar.messageservice.model.Result;
import com.adundar.messageservice.model.User;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public Result<User> retrieveUserByName(String userName) {
        return Result.fail(HttpStatus.SERVICE_UNAVAILABLE);

    }

}
