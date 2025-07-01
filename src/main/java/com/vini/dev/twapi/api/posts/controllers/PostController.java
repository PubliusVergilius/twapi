package com.vini.dev.twapi.api.posts.controllers;

import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostDTO;
import com.vini.dev.twapi.api.posts.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    PostService postService;

    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "erroooou!")
    static  class PostException extends RuntimeException {}

    @GetMapping("/{postId}")
    ResponseEntity<PostDTO> getPostById  (@PathVariable final String postId) {
        final Optional<PostDTO> post = this.postService.retrievePost(postId);

        return post.map(postDTO -> ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON).body(postDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @GetMapping("/error")
     ResponseEntity<String> error  () throws Exception {

        throw new PostException();
        /*
        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON).body(new PostDTO("", "", ""));
        */
    }

    @PostMapping
    ResponseEntity<PostDTO> createPost (@CookieValue(value = "userId", defaultValue = "") final String userId,
                                        @Valid @RequestBody final PostCreateDTO request) throws Exception {
        final PostDTO response = this.postService.registerPost(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Violação da integridade dos dados")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict (final HttpServletRequest request, final Exception ex) {
        PostController.log.error("Requisição: %s provocou %s".formatted(request.getRequestURL(), ex));
    }

    /*
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError (HttpServletRequest request, Exception ex) {
        log.error("Requisição: %s provocou %s".formatted(request.getRequestURL(), ex));

        ModelAndView mav = new ModelAndView();
        mav.addObject("Exceção", ex);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("erro");
        mav.setStatus(HttpStatus.BAD_REQUEST);
        return mav;
    };
    */
}

