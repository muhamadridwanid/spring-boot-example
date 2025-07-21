package com.muhamadridwan.bookservice.service.impl;

import com.muhamadridwan.bookservice.dto.PostResponse;
import com.muhamadridwan.bookservice.dto.UserResponse;
import com.muhamadridwan.bookservice.entity.Post;
import com.muhamadridwan.bookservice.entity.User;
import com.muhamadridwan.bookservice.repository.PostRepository;
import com.muhamadridwan.bookservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Page<PostResponse> getPosts(Long userId, Pageable pageable) {
        Page<Post> posts;
        if (userId == null) {
            posts = postRepository.findAll(pageable);
        } else {
            posts = postRepository.findByUserId(userId, pageable);
        }

        return posts.map(this::mapResponse);
    }


    private PostResponse mapResponse(Post post) {
        User user = post.getUser();
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());

        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setUser(userResponse);
        postResponse.setContent(post.getContent());
        postResponse.setTitle(post.getTitle());

        return postResponse;

    }
}
