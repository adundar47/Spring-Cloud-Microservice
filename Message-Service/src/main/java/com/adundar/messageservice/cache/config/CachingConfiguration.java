package com.adundar.messageservice.cache.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adundar.messageservice.cache.config.maps.CustomMapConfig;
import com.hazelcast.config.Config;

@Configuration
@EnableCaching
public class CachingConfiguration {

    @Value("${hazelcast.groupName}")
    private String        groupName;

    @Autowired
    List<CustomMapConfig> mapStoreList;

    @Bean
    public Config config() {
        Config conf = new Config();
        conf.getGroupConfig().setName(groupName);
        mapStoreList.forEach(mapStore -> mapStore.setMapConfig(conf));
        return conf;
    }
}
