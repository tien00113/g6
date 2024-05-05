package com.k35dl.g6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.k35dl.g6.models.Notifications;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long>{
    public Notifications findTopByOrderByTimestampDesc();
}
