package com.slay.Slay.entity;

import com.slay.Slay.Enum.ERole;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;

    public Role(Long id, ERole name, Set<UserEntity> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }
}
