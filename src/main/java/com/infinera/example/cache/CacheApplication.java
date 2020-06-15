package com.infinera.example.cache;

import com.infinera.example.cache.usingcacheofrediscache.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@Slf4j
@SpringBootApplication
public class CacheApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}

	@Autowired
	CacheService cacheService;

	@Override
	public void run(String... args) throws Exception {
		cacheService.cacheFromWithinSameClass();
		String firstString = cacheService.cacheThis();
		log.info("First: {}", firstString);
		String secondString = cacheService.cacheThis();
		log.info("Second: {}", secondString);
	}
}
