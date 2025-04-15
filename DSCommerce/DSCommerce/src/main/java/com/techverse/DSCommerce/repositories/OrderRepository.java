package com.techverse.DSCommerce.repositories;

import com.techverse.DSCommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
