package com.abhiram.orderrservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhiram.orderrservice.model.OrderTO;


@Repository
public interface OrderRepo extends JpaRepository<OrderTO, Long> {

}
