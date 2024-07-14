package com.slay.Slay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_site_subscription")
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "userSubscriptionc")
    private Subscription subscription;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active = true;

    public void setEndDate() {
        if (subscription.getDurationInDays() > 0) {
            this.endDate = this.startDate.plusDays(subscription.getDurationInDays());
        } else {
            this.endDate = null; // Для постоянных подписок
        }
    }

    public UserSubscription(Long id, UserEntity user, Subscription subscription, LocalDate startDate, LocalDate endDate, boolean active) {
        this.id = id;
        this.user = user;
        this.subscription = subscription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
    }
}
