package com.backend.OnlineStore.repository;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);


    List<Order> findByStatus(OrderStatus status);
}
