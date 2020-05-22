package org.example.repository;

import java.util.List;

import org.example.domain.Order;

public interface OrderRepository {

    void save(Order order);

    void delete(Order order);

    List<Order> getAll();

    Order getById(Integer id);
}