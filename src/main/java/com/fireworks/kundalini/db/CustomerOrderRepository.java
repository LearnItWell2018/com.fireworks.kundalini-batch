package com.fireworks.kundalini.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fireworks.kundalini.crud.resource.CustomerOrder;

public interface CustomerOrderRepository extends MongoRepository<CustomerOrder, String> {

}