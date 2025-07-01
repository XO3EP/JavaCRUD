package by.forum.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PostCreateDto {
    private String title;
    private String content;
    private Long userId;

    public PostCreateDto() {}

    public PostCreateDto(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
