package com.infinera.example.cache.redishashway;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
    @Cacheable(cacheNames = "students")
    Optional<Student> findByName(String name);
}
