package org.example.service;

import java.util.ArrayList;
import java.util.List;

import org.example.domain.Order;
import org.example.repository.ReadFromDataBase;

public class OrderServiceImpl implements OrderService {

  private final ReadFromDataBase readFromDataBase = new ReadFromDataBase();

  public void save(Order order) {
    readFromDataBase.save(order);
  }

  public void delete(int id) {
    readFromDataBase.delete(id);
  }

  public void addItemFromMenu(int id) {
    String sql = "SELECT id, title, price FROM shop.menu WHERE id=" + id;
    readFromDataBase.save(readFromDataBase.getFromMenuOrder(sql));
  }

  public List<Order> getAll() {
    ReadFromDataBase readFromDataBase = new ReadFromDataBase();
    return new ArrayList<>(readFromDataBase.getAll("SELECT * FROM shop.order"));
  }

  public List<Order> getMenu() {
    ReadFromDataBase readFromDataBase = new ReadFromDataBase();
    return new ArrayList<>(readFromDataBase.getAll("SELECT * FROM shop.menu"));
  }
}
