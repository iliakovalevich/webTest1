package org.example.repository;

import org.example.domain.Order;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class ReadFromDataBase {
    public void save(Order order) {
        try (Connection connection = dataBaseConnection()) {
            String sql = "INSERT INTO shop.order (title, price) Values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, order.getTitle());
            preparedStatement.setDouble(2, order.getPrice());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.getErrorCode();
        }
    }

    public void delete(int id) {
        try (Connection connection = dataBaseConnection()) {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM shop.order WHERE id = " + id;
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException ex) {
            ex.getErrorCode();
        }

    }

    public Order getFromMenuOrder(String sql) {
        Order order=new Order();
        try (Connection connection = dataBaseConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            order.setId(resultSet.getInt("id"));
            order.setTitle(resultSet.getString("title"));
            order.setPrice(resultSet.getDouble("price"));
            return order;
        } catch (SQLException ex) {
            ex.getErrorCode();
        }
        return order;
    }

    public List<Order> getAll(String sql) {
        List<Order> orders = new ArrayList<>();
        try {
            try (Connection connection = dataBaseConnection()) {
                Statement statement = Objects.requireNonNull(connection).createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setTitle(resultSet.getString("title"));
                    order.setPrice(resultSet.getDouble("price"));
                    orders.add(order);
                }
                connection.close();
                return orders;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Connection dataBaseConnection() {
        try {
            String url = "jdbc:mysql://localhost/shop?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "10033223391";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                return DriverManager.getConnection(url, username, password);
            }
        } catch (Exception ex) {
        }
        return null;
    }

}

