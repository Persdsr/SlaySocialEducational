package com.slay.Slay.controller;

import com.slay.Slay.service.FriendRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friend/")
public class UserFriendController {
    private final FriendRequestService friendRequestService;

    public UserFriendController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping("send")
    private ResponseEntity postFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {

        return friendRequestService.sendFriendRequest(senderId, receiverId);
    }

    @PostMapping("accept")
    private ResponseEntity acceptFriendRequest(@RequestParam Long friendRequestId) {

        return friendRequestService.acceptFriendRequest(friendRequestId);
    }

    @PostMapping("reject")
    private ResponseEntity rejectFriendRequest(@RequestParam Long friendRequestId) {

        return friendRequestService.rejectFriendRequest(friendRequestId);
    }

    @DeleteMapping("remove_friend")
    private ResponseEntity removeFriend(@RequestParam Long senderId, @RequestParam Long removeFriendId) {

        return friendRequestService.removeFriendBy(senderId, removeFriendId);
    }
}
