package by.forum.service.impl;

import by.forum.database.entity.Post;
import by.forum.database.entity.User;
import by.forum.database.repository.PostRepository;
import by.forum.database.repository.UserRepository;
import by.forum.dto.PostCreateDto;
import by.forum.dto.PostUpdateDto;
import by.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void createPost(PostCreateDto postCreateDto, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Post post = new Post();
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        post.setUserId(author);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id);
    }

    @Override
    public Post updatePost(Integer id, PostUpdateDto postUpdateDTO) {
        Optional<Post> existingPostOpt = postRepository.findById(id);
        if (existingPostOpt.isPresent()) {
            Post existingPost = existingPostOpt.get();
            if (postUpdateDTO.getTitle() != null) {
                existingPost.setTitle(postUpdateDTO.getTitle());
            }
            if (postUpdateDTO.getContent() != null) {
                existingPost.setContent(postUpdateDTO.getContent());
            }
            existingPost.setUpdatedAt(LocalDateTime.now());
            return postRepository.save(existingPost);
        }
        return null;
    }

    @Override
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}

