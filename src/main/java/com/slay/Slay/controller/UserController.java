package com.slay.Slay.controller;

import com.slay.Slay.entity.UserEntity;
import com.slay.Slay.exception.UserIdNotFoundException;
import com.slay.Slay.repository.UserRepository;
import com.slay.Slay.service.StorageService;
import com.slay.Slay.service.UserService;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.getAllUsers());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getUser(@PathVariable("userId") Long userId) {
        if (userService.getUserById(userId) != null) {
            return ResponseEntity.ok().body(userService.getUserById(userId));
        } else {
            throw new UserIdNotFoundException("User with id " + userId + " not found");
        }
    }

    @PostMapping("/register_user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registerUser(@RequestPart("userDetail") UserEntity userDetail,
                                       @RequestPart("avatar") MultipartFile avatar
                                        ) {
        return userService.addUser(userDetail, avatar);
    }

}