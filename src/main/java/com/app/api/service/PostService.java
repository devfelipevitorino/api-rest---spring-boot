package com.app.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.api.DTO.PostDTO;
import com.app.api.entity.Post;
import com.app.api.entity.User;
import com.app.api.repository.PostRepository;
import com.app.api.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	public String save(PostDTO postDTO) {
	    Post post = new Post();
	    
	    User user = userRepository.findById(postDTO.userID()) 
	            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	    
	    post.setContent(postDTO.content());
	    post.setMedia(postDTO.media());
	    post.setUser(user); 

	    postRepository.save(post); 
	    
	    return "Post feito com sucesso!";
	}
	
	public List<PostDTO> findAll() {
	    List<Post> postAll = postRepository.findAll();
	       
	    return postAll.stream()
	            .map(post -> new PostDTO( 
	                    post.getContent(),
	                    post.getMedia(),
	                    post.getUser().getId()
	            ))
	            .collect(Collectors.toList()); 
	}
	
	public String update(PostDTO postDTO, Long id) {	 
	    Post post = postRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Post não encontrado"));

	    User user = userRepository.findById(postDTO.userID())
	            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

	    post.setContent(postDTO.content());
	    post.setMedia(postDTO.media());
	    post.setUser(user); 

	    postRepository.save(post); 

	    return "Post atualizado com sucesso!";
	}
	
	public String delete(Long id) {
		postRepository.findById(id).orElseThrow();
		postRepository.deleteById(id);
		
		return "Post deletado com sucesso!";
	}
	
}
