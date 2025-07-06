package by.forum.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDto {
    private String title;
    private String content;
    private Long userId;
}
