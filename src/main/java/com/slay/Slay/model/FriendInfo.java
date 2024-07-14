package com.slay.Slay.model;

import com.slay.Slay.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FriendInfo {
    private String username;

    public static FriendInfo toModel(UserEntity user) {
        FriendInfo friendInfo = new FriendInfo();
        friendInfo.setUsername(user.getUsername());
        return friendInfo;
    }

    public FriendInfo(String username) {
        this.username = username;
    }
}
