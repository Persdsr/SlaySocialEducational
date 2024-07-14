package com.slay.Slay.controller;

import com.slay.Slay.entity.UserEntity;
import com.slay.Slay.exception.UserIdNotFoundException;
import com.slay.Slay.model.User;
import com.slay.Slay.repository.UserRepository;
import com.slay.Slay.service.StorageService;
import com.slay.Slay.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    StorageService storageService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void handleGetAllUsers_ReturnsValidResponseEntity() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(User.toModel(new UserEntity(
                "Persdsr",
                "Anahi.Leuschke@yahoo.com",
                "Anahi.Leuschke@yahoo.com",
                "Small Shoes withdrawal"
                )
                )));

        mockMvc.perform(get("/api/user/users"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                        [
                            {
                                    "username": "Persdsr",
                                    "aboutMe": "Small Shoes withdrawal",
                                    "friends": [],
                                    "institution": null
                                }
                        ]
                        """)
                );
    }

    @Test
    void handleGetUseById_ReturnsIsValid() throws Exception {
        when(userService.getUserById(3L)).thenReturn(new User("Clark", "Mockito", Set.of(), null));

        mockMvc.perform(get("/api/user/user/" + 3L)).andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json("""
                            {
                                "username": "Clark",
                                "aboutMe": "Mockito",
                                "friends": [],
                                "institution": null
                            }
                        """)
        );
    }

    @Test
    void handleGetUseById_ReturnsExceptionNotIsValid() throws Exception {
        when(userService.getUserById(3L)).thenReturn(null);

        mockMvc.perform(get("/api/user/user/" + 3L)).andExpectAll(
                status().isBadRequest(),
                result -> assertTrue(result.getResolvedException() instanceof UserIdNotFoundException),
                result -> assertEquals("User with id " + 3L + " not found", result.getResolvedException().getMessage())

        );
    }

    @Test
    void handleCreateNewUser_PayloadIsValid_ReturnsValidResponseEntity() throws Exception {
        MockMultipartFile avatar = new MockMultipartFile("avatar", "avatar.png", MediaType.IMAGE_PNG_VALUE, "image content".getBytes());
        MockMultipartFile userDetail = new MockMultipartFile("userDetail", "", MediaType.APPLICATION_JSON_VALUE, """
        {
            "username": "Persdsr2",
            "email": "reixter@bk.ru",
            "password": "Kekew228"
        }
    """.getBytes());

        // when(storageService.store(avatar)).thenReturn("avatar.png");

        this.mockMvc.perform(multipart("/api/user/register_user").file(avatar).file(userDetail))
                .andExpectAll(status().isCreated()
                );
    }
}