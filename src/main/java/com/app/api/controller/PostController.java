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

import com.app.api.DTO.PostDTO;
import com.app.api.entity.Post;
import com.app.api.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
	
@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping
	@Operation(summary = "Adds a new Post", description = "Adds a new Post",
	tags = {"Post"},
	responses = {
			@ApiResponse(description = "Created", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = PostDTO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internor Error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<PostDTO> save(@RequestBody PostDTO postDTO) {
	    try {
	        Post savedPost = postService.save(postDTO);
	        PostDTO responseDTO = new PostDTO(
	            savedPost.getContent(),
	            savedPost.getMedia(),
	            savedPost.getUser().getId()
	        );
	        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	@GetMapping
	@Operation(summary = "Finds all Post", description = "Finds all Post",
	tags = {"Post"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
						@Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = PostDTO.class))
							)
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internor Error", responseCode = "500", content = @Content)
	})
    public ResponseEntity<List<PostDTO>> findAll() {
        try {
            List<PostDTO> list = postService.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); 
        }
    }
	
	@PutMapping("/{id}")
	@Operation(summary = "Updates a Post", description = "Updates a Post",
	tags = {"Post"},
	responses = {
			@ApiResponse(description = "Updated", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = PostDTO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internor Error", responseCode = "500", content = @Content)
	})
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody PostDTO postDTO) {
        try {
            String message = postService.update(postDTO, id);
            return ResponseEntity.ok(message);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Post ou usuário não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar o post.");
        }
    }
	
	@DeleteMapping("/{id}")
	 @Operation(summary = "Deletes a Post", description = "Deletes a Post",
		tags = {"Post"},
		responses = {
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internor Error", responseCode = "500", content = @Content)
		})
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            String message = postService.delete(id);
            return ResponseEntity.ok(message);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Post não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar o post.");
        }
    }
	
}
