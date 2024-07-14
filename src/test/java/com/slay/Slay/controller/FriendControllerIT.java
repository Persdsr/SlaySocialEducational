package com.slay.Slay.controller;

import com.slay.Slay.Enum.EFriendStatus;
import com.slay.Slay.entity.FriendRequest;
import com.slay.Slay.entity.UserEntity;
import com.slay.Slay.repository.FriendRequestRepository;
import com.slay.Slay.repository.UserRepository;
import com.slay.Slay.service.FriendRequestService;
import com.slay.Slay.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FriendControllerIT {

    @Mock
    UserRepository userRepository;

    @Mock
    FriendRequestRepository friendRequestRepository;

    @Mock
    FriendRequestService friendRequestService;

    MockMvc mockMvc;

    @InjectMocks
    UserFriendController userFriendController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userFriendController).build();
    }

    @Test
    void handleSendFriendRequest_PayloadIsValid_ReturnsIsValidRequest() throws Exception {
        mockMvc.perform(post("/api/friend/send")
                .param("senderId", "1")
                .param("receiverId", "2")).andExpectAll(
                status().isOk()
        );
    }

    /*@Test
    void handleAcceptFriendRequest_PayloadIsValid_ReturnsValidRequest() throws Exception {
        when(friendRequestRepository.findById(1L)).thenReturn(new FriendRequest(1L, new UserEntity("Persdsr", "mkvfdmgkdf", "kmkgmdf"), new UserEntity("ReiXteR", "gfgdgfd", "gfgfd"), EFriendStatus.PENDING));

        mockMvc.perform(post("/api/friend/accept")
                .param("friendRequestId", "1")).andExpectAll(
                status().isOk()
        );
    }*/

}
