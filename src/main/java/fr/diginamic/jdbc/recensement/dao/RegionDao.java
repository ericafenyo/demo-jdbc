package fr.diginamic.jdbc.recensement.dao;

import fr.diginamic.jdbc.recensement.model.Region;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.diginamic.jdbc.recensement.Constants.CONNECTION_URI;
import static fr.diginamic.jdbc.recensement.Constants.PASSWORD;
import static fr.diginamic.jdbc.recensement.Constants.USER;

public class RegionDao implements Dao<Region> {

  @Override
  public int[] bulkInsert(List<Region> regions) throws SQLException {
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("INSERT IGNORE INTO regions (name, code) VALUES(?,?)");
    ) {
      for (Region region : regions) {
        statement.setString(1, region.name());
        statement.setInt(2, region.code());
        statement.addBatch();
      }
      return statement.executeBatch();
    }
  }

  @Override
  public List<Region> find() throws SQLException {
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM regions");
    ) {
      List<Region> regions = new ArrayList<>();

      try (ResultSet result = statement.executeQuery();) {
        while (result.next()) {
          int id = result.getInt("id");
          String name = result.getString("name");
          int code = result.getInt("code");
          regions.add(new Region(id, name, code));
        }
      }
      return regions;
    }
  }

  @Override
  public Map<String, Integer> reduce() throws SQLException {
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM regions");
    ) {
      try (ResultSet result = statement.executeQuery()) {
        Map<String, Integer> map = new HashMap<>();
        while (result.next()) {
          int id = result.getInt("id");
          String name = result.getString("name");
          map.put(name, id);
        }
        return map;
      }
    }
  }
}
