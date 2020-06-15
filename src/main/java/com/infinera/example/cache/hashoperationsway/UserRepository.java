package com.infinera.example.cache.hashoperationsway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserRepository {
    private HashOperations hashOperations;

    public UserRepository(RedisTemplate redisTemplateArg){
        this.hashOperations = redisTemplateArg.opsForHash();
    }

    public void save(User user){
        hashOperations.put("USER", user.getId(), user);
    }
    public List findAll(){
        return hashOperations.values("USER");
    }

    @Cacheable(cacheNames = "users")
    public User findById(String id){
        log.info("Fetch from Redis:" + id);
        return (User) hashOperations.get("USER", id);
    }

    public void update(User user){
        save(user);
    }

    @CacheEvict(cacheNames = "users")
    public void delete(String id){
        hashOperations.delete("USER", id);
    }
}
