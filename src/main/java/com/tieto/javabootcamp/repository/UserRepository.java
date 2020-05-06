package com.tieto.javabootcamp.repository;

import java.util.List;
import java.util.Optional;

import com.tieto.javabootcamp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByName(String name);
	Optional<List<User>> findAllByName(String name);
	Optional<Long> deleteByName(String name);




}
