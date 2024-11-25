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

import com.app.api.DTO.PostDTO;
import com.app.api.service.PostService;
	
@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping
	public String save(@RequestBody PostDTO postDTO) {
		try {
			String savedPost = postService.save(postDTO);
			return savedPost;
		} catch (Exception e) {
			return "Erro: " + e; 	
		}
	}
	
	@GetMapping
	public List<PostDTO> findAll(){
		try {
			List<PostDTO> list = postService.findAll();
			return list;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar usuários: " + e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, @RequestBody PostDTO postDTO) {
		try {
			String updatedPost = postService.update(postDTO, id);
			return updatedPost;
		} catch (Exception e) {
			return "Erro: " + e; 	
		}
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		try {
			String deletedPost = postService.delete(id);
			return deletedPost  ;
		} catch (Exception e) {
			return "Erro: " + e; 	
		}
	}
	
}
