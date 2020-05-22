package org.example.repository;

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
  private static final String PROPERTIES_DATA_BASE = "database.properties";
  private static final String SQL_SELECT_HoroscopeForecast = "SELECT DISTINCT Forecasts FROM HoroscopeForecast";
  private static final String SQL_SELECT_FORECAST  = "SELECT DISTINCT Forecasts FROM WeatherForecast";

  public List<String> readHoroscopeForecasts() {
    List<String> forecastsList = new ArrayList<>();
    try (Connection conn = getConnection()) {
      Statement statement = Objects.requireNonNull(conn).createStatement();
      ResultSet resultSet =
          statement.executeQuery(SQL_SELECT_HoroscopeForecast);
      while (resultSet.next()) {
        forecastsList.add(resultSet.getString("Forecasts"));
      }
      return forecastsList;

    } catch (Exception ex) {
      System.out.println(ex);
    }
    return null;
  }

  public List<String> readWeatherForecasts() {
    List<String> forecastsList = new ArrayList<>();
    try (Connection conn = getConnection()) {
      Statement statement = Objects.requireNonNull(conn).createStatement();
      ResultSet resultSet =
          statement.executeQuery(SQL_SELECT_FORECAST);
      while (resultSet.next()) {
        forecastsList.add(resultSet.getString("Forecasts"));
      }
      return forecastsList;
    } catch (Exception ex) {
      System.out.println(ex);
    }
    return null;
  }

  private Connection getConnection() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
    } catch (Exception ex) {
      System.out.println(ex);
    }
    File file = getFileFromResources(PROPERTIES_DATA_BASE);
    Properties properties = new Properties();
    try {
      properties.load(new FileReader(file));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String url = properties.getProperty("url");
    String username = properties.getProperty("username");
    String password = properties.getProperty("password");
    try {
      return DriverManager.getConnection(url, username, password);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  public File getFileFromResources(String fileName) {
    ClassLoader classLoader = ReadFromDataBase.class.getClassLoader();
    URL resource = classLoader.getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("file is not found!");
    } else {
      return new File(resource.getFile());
    }
  }
}
