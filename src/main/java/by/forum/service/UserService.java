package by.forum.service;

import by.forum.database.entity.User;
import by.forum.dto.UserUpdateDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {

    public List<User> getAllUsers();

    public User createUser(User user);

    public Optional<User> getUserById(Long id);

    public Optional<User> getUserByUsername(String username);

    public User updateUser(Long id, UserUpdateDto userUpdateDTO);

    public void deactivateUser(Long id);

    public void deleteUser(Long id);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    }
