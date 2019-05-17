package com.adundar.messageservice.cache.service;

import com.adundar.messageservice.model.User;

public interface UserCacheService {

    void save(User user);

    User get(String userName);

    void update(User user);

    void delete(String userName);

}
