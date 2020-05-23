package org.example.service;

import java.util.List;

import org.example.domain.Order;

public interface OrderService {

    void save(Order order);

    void delete(int id);

    List<Order> getAll();

}