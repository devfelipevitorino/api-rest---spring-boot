package com.app.api.controller;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.api.DTO.UserDTO;
import com.app.api.service.UserService;
	
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public String save(@RequestBody UserDTO userDTO) {
		try {
			String savedUser = userService.save(userDTO);
			return savedUser;
		} catch (Exception e) {
			return "Erro: " + e; 	
		}
	}
	
	@GetMapping
	public List<UserDTO> findAll(){
		try {
			List<UserDTO> list = userService.findAll();
			return list;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar usuários: " + e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
		try {
			String updatedUser = userService.update(userDTO, id);
			return updatedUser;
		} catch (Exception e) {
			return "Erro: " + e; 	
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		try {
			String deletedUser = userService.delete(id);
			return deletedUser;
		} catch (Exception e) {
			return "Erro: " + e; 	
		}
	}
	
}
