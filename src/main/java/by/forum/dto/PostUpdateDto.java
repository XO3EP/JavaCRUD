package by.forum.dto;

import by.forum.database.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PostUpdateDto {
    private String title;
    private String content;

    public PostUpdateDto() {}

    public PostUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostUpdateDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
