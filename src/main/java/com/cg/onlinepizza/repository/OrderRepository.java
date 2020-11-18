package com.cg.onlinepizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinepizza.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

//	public Set<Order> findByCustomer(String username);
}
