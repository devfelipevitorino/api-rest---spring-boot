package com.app.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.api.DTO.UserDTO;
import com.app.api.entity.User;
import com.app.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(UserDTO userDTO) {
	    User user = new User();
	    
	    user.setName(userDTO.name());
	    user.setEmail(userDTO.email());
	    user.setPassword(userDTO.password());
	    user.setFavoriteTeam(userDTO.favoriteTeam());

	    User savedUser = userRepository.save(user);
	    
	    return savedUser;
	}
	
	public List<UserDTO> findAll() {
	    List<User> userAll = userRepository.findAll();
	    return userAll.stream()
	            .map(user -> new UserDTO(
	                    user.getName(),
	                    user.getEmail(),
	                    null, 
	                    user.getFavoriteTeam()
	            ))
	            .collect(Collectors.toList());
	}
	
	public User update(UserDTO userDTO, Long id) {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

	    user.setName(userDTO.name());
	    user.setEmail(userDTO.email());
	    if (userDTO.password() != null && !userDTO.password().isEmpty()) {
	        user.setPassword(userDTO.password());
	    }
	    user.setFavoriteTeam(userDTO.favoriteTeam());

	    return userRepository.save(user);
	}
	
	public String delete(Long id) {
		userRepository.findById(id).orElseThrow();
		userRepository.deleteById(id);
		
		return "Usuário deletado com sucesso!";
	}
	
}
