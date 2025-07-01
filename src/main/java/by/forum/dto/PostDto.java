package by.forum.dto;

import by.forum.database.entity.Post;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;  // Только ID пользователя, если тебе не нужно все данные о пользователе




    public PostDto(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.userId = post.getUserId().getUserId();  // Только ID пользователя
    }

}

