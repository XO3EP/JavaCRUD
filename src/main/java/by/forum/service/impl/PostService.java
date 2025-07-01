package by.forum.service.impl;

import by.forum.database.entity.Post;
import by.forum.dto.PostCreateDto;
import by.forum.dto.PostUpdateDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public interface PostService {

    public List<Post> getAllPosts();
    public void createPost(PostCreateDto postCreateDto, String username);

    public Optional<Post> getPostById(Integer id);
    public Post updatePost(Integer id, PostUpdateDto postUpdateDTO);

    public void deletePost(Integer id);
}

