package fr.diginamic.jdbc.recensement.dao;

import fr.diginamic.jdbc.recensement.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.diginamic.jdbc.recensement.Constants.*;

public class DepartmentDao implements Dao<Department> {

  @Override
  public int[] bulkInsert(List<Department> departments) throws SQLException {
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("INSERT IGNORE INTO departments (code) VALUES(?)");
    ) {
      for (Department department : departments) {
        statement.setString(1, department.code());
        statement.addBatch();
      }
      return statement.executeBatch();
    }
  }

  @Override
  public List<Department> find() throws SQLException {
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM departments");
    ) {
      List<Department> departments = new ArrayList<>();

      try (ResultSet result = statement.executeQuery()) {
        while (result.next()) {
          int id = result.getInt("id");
          String code = result.getString("code");
          departments.add(new Department(id, code));
        }
      }
      return departments;
    }
  }

  @Override
  public Map<String, Integer> reduce() throws SQLException {
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM departments");
    ) {
      try (ResultSet result = statement.executeQuery()) {
        Map<String, Integer> map = new HashMap<>();
        while (result.next()) {
          int id = result.getInt("id");
          String code = result.getString("code");
          map.put(code, id);
        }
        return map;
      }
    }
  }
}
