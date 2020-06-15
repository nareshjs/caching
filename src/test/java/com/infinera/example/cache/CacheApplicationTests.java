package com.infinera.example.cache;

import com.infinera.example.cache.hashoperationsway.User;
import com.infinera.example.cache.hashoperationsway.UserRepository;
import com.infinera.example.cache.redishashway.Student;
import com.infinera.example.cache.redishashway.StudentRepository;
import com.infinera.example.cache.usingcacheofrediscache.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootTest
class CacheApplicationTests {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	UserRepository userRepository;

	@Test
	void redisHashAndRepositoryWay() {
		Student student = Student.builder().id("Eng2015001").name("John Doe").gender(Student.Gender.MALE).grade(1).build();
		studentRepository.save(student);

		Student retrievedStudent =
				studentRepository.findById("Eng2015001").get();

		retrievedStudent.setName("Richard Watson");
		studentRepository.save(retrievedStudent);

		Student anotherStudent = new Student("ABC", "NJ", Student.Gender.MALE, 2);
		studentRepository.save(anotherStudent);
		Student fetchedStudent = studentRepository.findByName("NJ").get();

		studentRepository.delete(fetchedStudent);
		studentRepository.deleteById(student.getId());
	}

	@Test
	void hashOperationsWay() {
		User user = new User();
		user.setId("U1");
		user.setName("User1");
		userRepository.save(user);

		User fetchedUser = userRepository.findById("U1");
		User fetchedUser1 = userRepository.findById("U1");
		fetchedUser.setId("U11");
		fetchedUser.setName("User11");
		userRepository.save(fetchedUser);

		userRepository.delete("U1");
		userRepository.delete("U11");
	}

}
