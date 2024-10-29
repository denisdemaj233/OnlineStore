package com.backend.OnlineStore.repository;

import com.backend.OnlineStore.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    Optional<OrderLine> findById(Long orderId);

}
