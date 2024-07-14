package com.slay.Slay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Поле username - обязательно")
    @Size(min = 4, max = 18)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "username должен иметь только латинские буквы")
    private String username;

    @NotNull(message = "Поле email - обязательно")
    @Email(message = "Email не соответсвует требованиям")
    private String email;

    @NotNull(message = "Поле password - обязательно")
    @Size(min = 7, max = 30)
    private String password;

    @NotNull(message = "Поле Имя - обязательно")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]+", message = "Поле Имя должно содержать только буквы")
    @Size(min = 2, max = 15)
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Поле фамилия - обязательно")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]+", message = "Поле Фамилия должно содержать только буквы")
    @Size(min = 2, max = 15)
    @Column(name = "second_name")
    private String secondName;

    @NotNull(message = "Поле отчество - обязательно")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]+", message = "Поле Отчество должно содержать только буквы")
    @Size(min = 2, max = 15)
    private String patronymic;

    @NotNull(message = "Поле день рождение - обязательно")
    @Past(message = "День рождение не может быть в будущем")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthday;

    @Size(max = 2000)
    private String aboutMe;

    @NotNull(message = "Поле телефонный номер - обязательно")
    @Pattern(regexp = "\\d+", message = "Поле должно содержать только цифры")
    @Column(name = "mobile_phone")
    private String mobilePhone;

    private String gender;

    private String avatar;

    private Long rating = 0L;

    @NotNull(message = "Поле город - обязательно")
    @Size(min = 2, max = 50)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution")
    private InstitutionEntity institution;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_subscription", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "institution_id"))
    private Set<InstitutionEntity> institutionSubscription = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subscription", joinColumns = @JoinColumn(name = "subscriber_id"), inverseJoinColumns = @JoinColumn(name = "subscribed_to_id"))
    private Set<UserEntity> subscribers = new HashSet<>();

    @OneToMany(mappedBy = "author")
    private Set<PostUserEntity> posts = new HashSet<>();

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<FriendRequest> sentFriendRequest = new HashSet<>();

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<FriendRequest> receivedFriendRequest = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "user_friends",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "friend_id"))
    @JsonIgnore
    private Set<UserEntity> friends = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserSubscription> subscriptions = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "editing_institutions",
            joinColumns = @JoinColumn(name = "editor_id"),
            inverseJoinColumns = @JoinColumn(name = "institution_id")
    )
    private Set<InstitutionEntity> editing;

    public UserEntity(Long id, String username, String email, String password, String firstName, String secondName, String patronymic, Date birthday, String aboutMe, String mobilePhone, String gender, String avatar, Long rating, String city, InstitutionEntity institution, Set<InstitutionEntity> institutionSubscription, Set<UserEntity> subscribers, Set<PostUserEntity> posts, Set<FriendRequest> sentFriendRequest, Set<FriendRequest> receivedFriendRequest, Set<Role> roles, Set<UserSubscription> subscriptions, Set<InstitutionEntity> editing) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.aboutMe = aboutMe;
        this.mobilePhone = mobilePhone;
        this.gender = gender;
        this.avatar = avatar;
        this.rating = rating;
        this.city = city;
        this.institution = institution;
        this.institutionSubscription = institutionSubscription;
        this.subscribers = subscribers;
        this.posts = posts;
        this.sentFriendRequest = sentFriendRequest;
        this.receivedFriendRequest = receivedFriendRequest;
        this.roles = roles;
        this.subscriptions = subscriptions;
        this.editing = editing;
    }

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserEntity(String username, String email, String password, String aboutMe) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.aboutMe = aboutMe;
    }

    public UserEntity(Long id, String username, String aboutMe, Set<UserEntity> friends, InstitutionEntity institution) {
        this.id = id;
        this.username = username;
        this.aboutMe = aboutMe;
        this.friends = friends;
        this.institution = institution;
    }
}