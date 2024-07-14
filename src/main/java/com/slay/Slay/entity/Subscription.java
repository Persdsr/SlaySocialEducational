package com.slay.Slay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "site_subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20)
    private String name;

    @Column(name = "duration_days")
    private Integer durationInDays;

    @OneToMany(mappedBy = "subscription")
    private Set<UserSubscription> userSubscription = new HashSet<>();

    public Subscription(Long id, String name, Integer durationInDays, Set<UserSubscription> userSubscription) {
        this.id = id;
        this.name = name;
        this.durationInDays = durationInDays;
        this.userSubscription = userSubscription;
    }
}