package com.slay.Slay.controller;

import com.slay.Slay.repository.FriendRequestRepository;
import com.slay.Slay.repository.UserSubscriptionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscription_user")
public class SubscriptionUserController {

    private final FriendRequestRepository friendRequestRepository;

    public SubscriptionUserController(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }


}
