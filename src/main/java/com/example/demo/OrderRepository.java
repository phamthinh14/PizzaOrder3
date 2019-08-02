package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<PizzaOrder, Long> {
//    void deleteByUserID();
    Iterable<PizzaOrder> findAllByUser(User user);
}
