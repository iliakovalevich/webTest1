package org.example.service;

import java.util.ArrayList;
import java.util.List;

import org.example.domain.Order;
import org.example.repository.ReadFromDataBase;

public class OrderServiceImpl implements OrderService {

    private ReadFromDataBase readFromDataBase = new ReadFromDataBase();

    public void save(Order order) {
        readFromDataBase.save(order);
    }

    public void delete(int id) {
        readFromDataBase.delete(id);
    }

    public void addItemFromMenu(int id){
        String sql = "SELECT id, title, price FROM shop.menu WHERE id = "+id;
        readFromDataBase.save(readFromDataBase.getFromMenuOrder(sql));
    }

    public List<Order> getAll() {
        ReadFromDataBase readFromDataBase = new ReadFromDataBase();
        List<Order> orders = new ArrayList<Order>();
        orders.addAll(readFromDataBase.getAll("SELECT * FROM shop.order"));
        return orders;
    }

    public List<Order> getMenu() {
        ReadFromDataBase readFromDataBase = new ReadFromDataBase();
        List<Order> menus = new ArrayList<Order>();
        menus.addAll(readFromDataBase.getAll("SELECT * FROM shop.menu"));
        return menus;
    }
}


