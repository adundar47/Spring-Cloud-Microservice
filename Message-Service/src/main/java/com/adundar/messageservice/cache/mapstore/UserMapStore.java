package com.adundar.messageservice.cache.mapstore;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.adundar.messageservice.client.UserClient;
import com.adundar.messageservice.model.Result;
import com.adundar.messageservice.model.User;
import com.hazelcast.core.MapStore;

@Repository
public class UserMapStore implements MapStore<String, User> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    UserClient           userServiceClient;

    public UserMapStore(UserClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public void store(String s, User user) {

    }

    @Override
    public void storeAll(Map<String, User> map) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void deleteAll(Collection<String> collection) {

    }

    @Override
    public User load(String s) {
        LOGGER.debug("[load]: Routing message request to User Service. userId: {}", s);
        Result<User> result;
        try {
            result = userServiceClient.retrieveUserByName(s);
            LOGGER.debug("[load]: User Service result is -> {}", result.toString());
        } catch (Exception e) {
            return null;
        }

        return result.getPayload();
    }

    @Override
    public Map<String, User> loadAll(Collection<String> collection) {
        return null;
    }

    @Override
    public Set<String> loadAllKeys() {
        return null;
    }
}
