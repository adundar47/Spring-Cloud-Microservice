package com.adundar.messageservice.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.adundar.messageservice.model.User;
import com.hazelcast.core.HazelcastInstance;

@Component
public class UserCacheManager implements UserCacheService {

    @Value("${hazelcast.userMapName}")
    private String            userMap;

    @Autowired
    private HazelcastInstance hzInstance;

    @Override
    public void save(User user) {
        hzInstance.getMap(userMap).set(user.getName(), user);
    }

    @Override
    public User get(String userName) {
        return (User) hzInstance.getMap(userMap).get(userName);
    }

    @Override
    public void update(User user) {
        hzInstance.getMap(userMap).delete(user.getName());
        hzInstance.getMap(userMap).put(user.getName(), user);
    }

    @Override
    public void delete(String userName) {
        hzInstance.getMap(userMap).delete(userName);
    }
}
