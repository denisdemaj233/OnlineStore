package com.backend.OnlineStore.repository;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long orderId);
}
