package com.app.api.service;

import java.util.List;
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

	public String save(UserDTO userDTO) {
	    User user = new User();
	    
	    user.setName(userDTO.name());
	    user.setEmail(userDTO.email());
	    user.setPassword(userDTO.password());
	    user.setFavoriteTeam(userDTO.favoriteTeam());

	    User savedUser = userRepository.save(user);
	    
	    String message = "Usuário " + savedUser.getName() + " cadastrado com sucesso!";
	    
	    return message;
	}
	
	public List<UserDTO> findAll(){
		List<User> userAll = userRepository.findAll();
		return userAll.stream()
				.map(User -> new UserDTO(
						User.getName(),
						User.getEmail(),
						User.getPassword(),
						User.getFavoriteTeam()))
				.collect(Collectors.toList());
	}
	
	public String update(UserDTO userDTO, Long id) {
		 User user = userRepository.findById(id).orElseThrow();
		 
		 user.setName(userDTO.name());
		 user.setEmail(userDTO.email());
		 user.setPassword(userDTO.password());
		 user.setFavoriteTeam(userDTO.favoriteTeam());
		 
		 userRepository.save(user);
		 
		 return user.getName() + " atualizado com sucesso!";
	}
	
	public String delete(Long id) {
		userRepository.findById(id).orElseThrow();
		userRepository.deleteById(id);
		
		return "Usuário deletado com sucesso!";
	}
	
}
