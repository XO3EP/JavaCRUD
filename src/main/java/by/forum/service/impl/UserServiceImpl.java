package by.forum.service.impl;

import by.forum.database.entity.Role;
import by.forum.database.entity.User;
import by.forum.database.repository.UserRepository;
import by.forum.dto.UserUpdateDto;
import by.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setIsActive(true);
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(Math.toIntExact(id));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(Long id, UserUpdateDto userUpdateDTO) {
        Optional<User> existingUserOpt = userRepository.findById(Math.toIntExact(id));
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            if (userUpdateDTO.getUsername() != null) {
                existingUser.setUsername(userUpdateDTO.getUsername());
            }
            if (userUpdateDTO.getEmail() != null) {
                existingUser.setEmail(userUpdateDTO.getEmail());
            }
            if (userUpdateDTO.getPassword() != null) {
                existingUser.setPassword(userUpdateDTO.getPassword());
            }
            if (userUpdateDTO.getIsActive() != null) {
                existingUser.setIsActive(userUpdateDTO.getIsActive());
            }
            if (userUpdateDTO.getRole() != null) {
                existingUser.setRole(userUpdateDTO.getRole());
            }

            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deactivateUser(Long id) {
        Optional<User> userOpt = userRepository.findById(Math.toIntExact(id));
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setIsActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failure to retrieve user" + username));
    }
}
