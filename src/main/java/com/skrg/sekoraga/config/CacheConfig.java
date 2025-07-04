package com.skrg.sekoraga.config;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skrg.sekoraga.repository.AdUserRepository;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache(AdUserRepository.USERS_BY_USERNAME_CACHE),
                new ConcurrentMapCache(AdUserRepository.USERS_BY_EMAIL_CACHE)));
        return cacheManager;
    }

}
