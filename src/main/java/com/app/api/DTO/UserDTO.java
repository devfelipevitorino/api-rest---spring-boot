package com.app.api.DTO;

public record UserDTO(
		String name, 
		String email, 
		String password, 
		String favoriteTeam
) {}
