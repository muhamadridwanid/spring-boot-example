package com.muhamadridwan.bookservice.service.impl;

import com.muhamadridwan.bookservice.dto.PostResponse;
import com.muhamadridwan.bookservice.entity.Post;
import com.muhamadridwan.bookservice.entity.User;
import com.muhamadridwan.bookservice.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void getPosts_shouldReturnAllPosts_whenUserIdIsNull() {
        Pageable pageable = PageRequest.of(0, 10);

        Post post = new Post();
        post.setId(1L);
        post.setTitle("Title");
        post.setContent("Content");

        User user = new User();
        user.setId(100L);
        user.setName("Ridwan");
        user.setEmail("ridwan@mail.com");
        post.setUser(user);

        Page<Post> postPage = new PageImpl<>(List.of(post));

        when(postRepository.findAll(pageable)).thenReturn(postPage);

        Page<PostResponse> result = postService.getPosts(null, pageable);

        assertEquals(1, result.getTotalElements());
        PostResponse postResponse = result.getContent().get(0);
        assertEquals("Title", postResponse.getTitle());
        assertEquals("Ridwan", postResponse.getUser().getName());
    }

    @Test
    void getPosts_shouldReturnFilteredPosts_whenUserIdIsProvided() {
        Pageable pageable = PageRequest.of(0, 10);
        Long userId = 200L;

        Post post = new Post();
        post.setId(2L);
        post.setTitle("Filtered Title");
        post.setContent("Filtered Content");

        User user = new User();
        user.setId(userId);
        user.setName("User 200");
        user.setEmail("user200@mail.com");
        post.setUser(user);

        Page<Post> postPage = new PageImpl<>(List.of(post));

        when(postRepository.findByUserId(userId, pageable)).thenReturn(postPage);

        Page<PostResponse> result = postService.getPosts(userId, pageable);

        assertEquals(1, result.getTotalElements());
        PostResponse postResponse = result.getContent().get(0);
        assertEquals("Filtered Title", postResponse.getTitle());
        assertEquals("User 200", postResponse.getUser().getName());
    }
}