package com.techverse.DSCommerce.repositories;

import com.techverse.DSCommerce.entities.OrderItem;
import com.techverse.DSCommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
