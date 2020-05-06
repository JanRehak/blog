package com.tieto.javabootcamp.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.tieto.javabootcamp.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	@Override
	Set<Role> findAll();
	
	Set<Role> findByName(String name);

}
