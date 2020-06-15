package com.infinera.example.cache.usingcacheofrediscache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CacheService {
    @Cacheable(cacheNames = "myCache")
    public String cacheThis(){
        log.info("Returning NOT from cache!");
        return "this Is it";
    }

    //No error but not cached
    public void cacheFromWithinSameClass()
    {
        String firstString = cacheThis();
        log.info("Within First: {}", firstString);
        String secondString = cacheThis();
        log.info("Within Second: {}", secondString);
    }
}
