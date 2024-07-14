package com.slay.Slay.service;

import com.slay.Slay.Enum.EFriendStatus;
import com.slay.Slay.entity.FriendRequest;
import com.slay.Slay.entity.UserEntity;
import com.slay.Slay.repository.FriendRequestRepository;
import com.slay.Slay.repository.UserRepository;
import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    public FriendRequestService(FriendRequestRepository friendRequestRepository, UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity sendFriendRequest(Long senderId, Long receiverId) {
        Optional<UserEntity> senderUser = userRepository.findById(senderId);
        Optional<UserEntity> receiverUser = userRepository.findById(receiverId);
        FriendRequest friendRequest = new FriendRequest();

        if (senderUser.isPresent() && receiverUser.isPresent()) {
            friendRequest.setSender(senderUser.get());
            friendRequest.setReceiver(receiverUser.get());
        } else {
            return ResponseEntity.badRequest().body("BAD");
        }

        friendRequest.setStatus(EFriendStatus.PENDING);
        friendRequestRepository.save(friendRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("ok");
    }

    public ResponseEntity acceptFriendRequest(Long friendRequestId) {
        Optional<FriendRequest> friendRequest = friendRequestRepository.findById(friendRequestId);
        if (friendRequest.isPresent() && friendRequest.get().getStatus() == EFriendStatus.PENDING) {
            FriendRequest request = friendRequest.get();
            request.setStatus(EFriendStatus.ACCEPTED);
            UserEntity sender = request.getSender();
            UserEntity receiver = request.getReceiver();

            sender.getFriends().add(receiver);
            receiver.getFriends().add(sender);

            userRepository.save(sender);
            userRepository.save(receiver);
            friendRequestRepository.save(friendRequest.get());
        } else {
            throw new IllegalArgumentException("Invalid user IDs");
        }

        return ResponseEntity.ok().body("Все ок");
    }

    public ResponseEntity rejectFriendRequest(Long friendRequestId) {
        Optional<FriendRequest> friendRequest = friendRequestRepository.findById(friendRequestId);
        if (friendRequest.isPresent() && friendRequest.get().getStatus() == EFriendStatus.PENDING) {
            FriendRequest request = friendRequest.get();
            request.setStatus(EFriendStatus.DECLINED);

            friendRequestRepository.delete(request);
        } else {
            throw new IllegalArgumentException("Invalid user IDs");
        }

        return ResponseEntity.ok().body("Все ок");
    }

    public ResponseEntity removeFriendBy(Long senderId, Long removeFriendId) {
        Optional<UserEntity> senderUserOpt = userRepository.findById(senderId);
        Optional<UserEntity> removeUserFriendOpt = userRepository.findById(removeFriendId);

        if (senderUserOpt.isPresent() && removeUserFriendOpt.isPresent()) {
            UserEntity senderUser = senderUserOpt.get();
            UserEntity removeFriend = removeUserFriendOpt.get();

            senderUser.getFriends().remove(removeFriend);
            removeFriend.getFriends().remove(senderUser);

            userRepository.save(senderUser);
            userRepository.save(removeFriend);

            return ResponseEntity.ok("OK");
        } else {
            throw new IllegalArgumentException("Invalid user IDs");
        }
    }
}
