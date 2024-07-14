package com.slay.Slay.model;

import com.slay.Slay.entity.InstitutionEntity;
import com.slay.Slay.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String username;
    private String aboutMe;
    private Set<FriendInfo> friends;
    private InstitutionEntity institution;

    public static User toModel(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setAboutMe(userEntity.getAboutMe());
        user.setInstitution(userEntity.getInstitution());

        Set<FriendInfo> userFriends = new HashSet<>();

        for (UserEntity userEntityFriend: userEntity.getFriends()) {
            userFriends.add(FriendInfo.toModel(userEntityFriend));
        }

        user.setFriends(userFriends);

        return user;
    }

    public User(String username, String aboutMe, Set<FriendInfo> friends, InstitutionEntity institution) {
        this.username = username;
        this.aboutMe = aboutMe;
        this.friends = friends;
        this.institution = institution;
    }
}
