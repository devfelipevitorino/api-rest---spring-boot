package com.app.api.controller;
	
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.api.DTO.UserDTO;
import com.app.api.entity.User;
import com.app.api.service.UserService;
	
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	 @PostMapping
	    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
	        try {
	            User savedUser = userService.save(userDTO);
	            UserDTO responseDTO = new UserDTO(
	                savedUser.getName(),
	                savedUser.getEmail(),
	                null, 
	                savedUser.getFavoriteTeam()
	            );
	            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	    @GetMapping
	    public ResponseEntity<List<UserDTO>> findAll() {
	        try {
	            List<UserDTO> list = userService.findAll();
	            return ResponseEntity.ok(list);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
	        try {
	            User updatedUser = userService.update(userDTO, id);
	            UserDTO responseDTO = new UserDTO(
	                updatedUser.getName(),
	                updatedUser.getEmail(),
	                null, 
	                updatedUser.getFavoriteTeam()
	            );
	            return ResponseEntity.ok(responseDTO);
	        } catch (NoSuchElementException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
	        try {
	            String message = userService.delete(id);
	            return ResponseEntity.ok(message);
	        } catch (NoSuchElementException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
}
