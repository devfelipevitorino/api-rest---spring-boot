package com.app.api.DTO;

public record PostDTO(
		String content,
		String media,
		Long userID
) {}
