package com.slay.Slay.service;

import com.slay.Slay.entity.Subscription;
import com.slay.Slay.entity.UserEntity;
import com.slay.Slay.entity.UserSubscription;
import com.slay.Slay.repository.SubscriptionRepository;
import com.slay.Slay.repository.UserRepository;
import com.slay.Slay.repository.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionService {
    private final UserSubscriptionRepository userSubscriptionRepository;

    public SubscriptionService(UserSubscriptionRepository userSubscriptionRepository, UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userSubscriptionRepository = userSubscriptionRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }
    private final UserRepository userRepository;

    private final SubscriptionRepository subscriptionRepository;


    public void addSubscriptionToUser(Long userId, Long subscriptionId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new RuntimeException("Subscription not found"));

        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setUser(user);
        userSubscription.setSubscription(subscription);
        userSubscription.setStartDate(LocalDate.now());
        userSubscription.setEndDate();

        user.getSubscriptions().add(userSubscription);
        userRepository.save(user);
    }

    @Scheduled(cron = "0 0 0 * * ?") // Запуск каждый день в полночь
    public void deactivateExpiredSubscriptions() {
        LocalDate today = LocalDate.now();
        List<UserSubscription> expiredSubscriptions = userSubscriptionRepository.findAllByEndDateBeforeAndActive(today, true);

        for (UserSubscription subscription : expiredSubscriptions) {
            subscription.setActive(false);
            userSubscriptionRepository.save(subscription);
        }
    }

}