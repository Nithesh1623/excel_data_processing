package com.excel.excel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.excel.excel.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    
}
