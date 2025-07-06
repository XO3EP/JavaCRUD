package by.forum.dto;

import by.forum.database.entity.Post;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateDto {
    private String title;
    private String content;

    public PostUpdateDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
