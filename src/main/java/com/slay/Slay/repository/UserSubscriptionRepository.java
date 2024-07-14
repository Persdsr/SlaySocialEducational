package com.slay.Slay.repository;

import com.slay.Slay.entity.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    List<UserSubscription> findAllByEndDateBeforeAndActive(LocalDate endDate, boolean active);
}