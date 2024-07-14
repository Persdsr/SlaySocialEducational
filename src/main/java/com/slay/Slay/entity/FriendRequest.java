package com.slay.Slay.entity;

import com.slay.Slay.Enum.EFriendStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_friend_request")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @ManyToOne()
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    @Enumerated(EnumType.STRING)
    private EFriendStatus status;

    public FriendRequest(Long id, UserEntity sender, UserEntity receiver, EFriendStatus status) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

}
