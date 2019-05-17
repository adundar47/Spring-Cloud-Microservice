package com.adundar.messageservice.cache.config.maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adundar.messageservice.cache.mapstore.UserMapStore;
import com.adundar.messageservice.client.UserClient;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.config.MaxSizeConfig;

@Component
public class UserMapConfig implements CustomMapConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${hazelcast.userMapName}")
    private String       userMap;

    @Value("${hazelcast.writeDelaySeconds}")
    private int          writeDelaySeconds;

    @Autowired
    private UserClient   userClient;

    private Config       config;

    @Override
    public void setMapConfig(Config config) {
        this.config = config;
        integrateWithStorage();
    }

    private MapConfig configureMap() {
        MapConfig mapConfig = config.getMapConfig(userMap);
        if (mapConfig == null) {
            mapConfig = new MapConfig(userMap);
            mapConfig.setMaxSizeConfig(new MaxSizeConfig(10, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_PERCENTAGE));
            mapConfig.setEvictionPolicy(EvictionPolicy.LFU);
            config.addMapConfig(mapConfig);
        }
        return mapConfig;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private UserMapStore getUserMapStore() {
        return new UserMapStore(userClient);
    }

    private MapStoreConfig createMapStoreConfig(int writeToDelay, MapStoreConfig.InitialLoadMode loadMode) {
        MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig.setImplementation(getUserMapStore());
        mapStoreConfig.setWriteDelaySeconds(writeToDelay);
        mapStoreConfig.setInitialLoadMode(loadMode);
        return mapStoreConfig;
    }

    private void integrateWithStorage() {
        MapConfig mapConfig = configureMap();
        MapStoreConfig mapStoreConfig = mapConfig.getMapStoreConfig();
        if (mapStoreConfig == null || !mapStoreConfig.isEnabled()) {
            mapStoreConfig = this.createMapStoreConfig(writeDelaySeconds, MapStoreConfig.InitialLoadMode.LAZY);
            mapConfig.setMapStoreConfig(mapStoreConfig);
            logger.info("[integrateWithStorage] {} is integrated with mapstore", userMap);
        }
    }

}
