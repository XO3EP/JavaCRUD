package by.forum.dto;

import by.forum.database.entity.Role;
import lombok.Data;

@Data
public class UserUpdateDto {
    private String username;
    private String email;
    private String password;
    private Boolean isActive;
    private Role role;
}
