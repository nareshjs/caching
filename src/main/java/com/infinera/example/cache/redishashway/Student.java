package com.infinera.example.cache.redishashway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

//If you dont specify the tag value as part of redis hash, the default is the fully qualified class name when seen in Redis
@RedisHash("Student")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Student implements Serializable {

    public enum Gender {
        MALE, FEMALE
    }

    //No need to specify @Id since the attribute name is id
    @Id
    private String id;
    @Indexed
    private String name;
    private Gender gender;
    private int grade;

}
