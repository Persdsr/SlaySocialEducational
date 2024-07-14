package com.slay.Slay.controller;

import com.slay.Slay.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/user/{userId}/add/{subscriptionId}")
    public ResponseEntity<?> addSubscriptionToUser(@PathVariable Long userId, @PathVariable Long subscriptionId) {
        subscriptionService.addSubscriptionToUser(userId, subscriptionId);
        return ResponseEntity.ok("Subscription added successfully");
    }

}