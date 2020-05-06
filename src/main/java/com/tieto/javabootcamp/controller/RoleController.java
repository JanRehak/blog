package com.tieto.javabootcamp.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tieto.javabootcamp.model.Role;
import com.tieto.javabootcamp.repository.RoleRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired RoleRepository roleRepository;
	
	@GetMapping public Set<Role> listRoles() {
		return roleRepository.findAll();
	}
}
