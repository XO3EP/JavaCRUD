package by.forum.dto;

import by.forum.database.entity.Role;
import lombok.Data;
import lombok.Value;

@Data
public class UserUpdateDto {
    private String username;
    private String email;
    private String password;
    private Boolean isActive;
    private Role role;
}
