package by.forum.controller;

import by.forum.database.entity.Post;
import by.forum.dto.PostCreateDto;
import by.forum.dto.PostDto;
import by.forum.dto.PostUpdateDto;
import by.forum.service.impl.PostServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostServiceImpl postServiceImpl;

    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getAllPostsHtml(Model model, Principal principal) {
        List<Post> posts = postServiceImpl.getAllPosts();
        model.addAttribute("posts", posts);

        if (principal != null) {
            model.addAttribute("currentUsername", principal.getName());
        }

        return "post/posts";
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        List<Post> posts = postServiceImpl.getAllPosts();
        return posts.stream().map(PostDto::new).collect(Collectors.toList());
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new PostCreateDto());
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostCreateDto postCreateDto,
                             Principal principal) {
        postServiceImpl.createPost(postCreateDto, principal.getName());
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer id) {
        return postServiceImpl.getPostById(id)
                .map(post -> ResponseEntity.ok(new PostDto(post)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model, Principal principal) {
        Post post = postServiceImpl.getPostById(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found"));

        if (!post.getUserId().getUsername().equals(principal.getName()) && !principal.getName().equals("admin")) {
            return "redirect:/posts";
        }

        model.addAttribute("post", post);
        return "post/edit";
    }

    @PostMapping("/{id}/update")
    public String updatePost(@PathVariable Integer id,
                             @RequestParam String title,
                             @RequestParam String content) {
        PostUpdateDto updateDto = new PostUpdateDto(title, content);
        postServiceImpl.updatePost(id, updateDto);
        return "redirect:/posts";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Integer id) {
        postServiceImpl.deletePost(id);
        return "redirect:/posts";
    }


}

