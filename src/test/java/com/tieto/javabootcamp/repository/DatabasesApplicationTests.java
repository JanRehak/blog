package com.tieto.javabootcamp.repository;

import com.tieto.javabootcamp.DatabaseApplication;
import com.tieto.javabootcamp.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

@Transactional
@SpringBootTest(classes = DatabaseApplication.class)
public class DatabasesApplicationTests {

	@Autowired 
	UserRepository repository;
	User user;

	@BeforeEach
	public void init() {
		user = new User("David", "", Set.of());
		repository.deleteAll();
	}

	@Test
	@DisplayName("Saved user can be find.")
	public void findSavedUserByName() {

		user = repository.save(user);

		assertThat(repository.findByName(user.getName())).hasValue(user);

	}


}
