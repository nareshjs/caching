package com.infinera.example.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class CacheConfiguration {
    @Bean
    JedisConnectionFactory cacheConnectionFactory() {
        //Create RedisSentinelConfiguration for cluster config.
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(3);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        return connectionFactory;
    }

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate() {
        //RedisTemplate used default Java serialization
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(cacheConnectionFactory());
        //Instead of default Java Serialization
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    //Uncomment to show usage of ConcurrentMapCacheManager
    /*@Bean
    public CacheManager cacheManager() {
        CacheManager cacheManager = null;
        cacheManager = new ConcurrentMapCacheManager("myCache", "users", "students");
        return cacheManager;
    }*/
}
