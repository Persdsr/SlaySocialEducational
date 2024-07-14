package com.slay.Slay.service;

import com.slay.Slay.entity.UserEntity;
import com.slay.Slay.exception.UserIdNotFoundException;
import com.slay.Slay.model.User;
import com.slay.Slay.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final StorageService storageService;

    public UserService(StorageService storageService, UserRepository userRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (UserEntity userEntity: userRepository.findAll()) {
            users.add(User.toModel(userEntity));
        }
        return users;
    }

    public ResponseEntity addUser(UserEntity user, MultipartFile avatar) {
        String nameAvatar = storageService.store(avatar);

        String uriAvatar = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(nameAvatar)
                .toUriString();

        user.setAvatar(uriAvatar);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User is created");
    }

    public User getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity != null) {
            return User.toModel(userEntity);
        } else {
            return null;
        }
    }
}
