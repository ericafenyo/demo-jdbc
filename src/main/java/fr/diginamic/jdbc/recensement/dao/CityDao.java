package fr.diginamic.jdbc.recensement.dao;

import fr.diginamic.jdbc.recensement.model.City;
import fr.diginamic.jdbc.recensement.model.Department;
import fr.diginamic.jdbc.recensement.model.Region;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static fr.diginamic.jdbc.recensement.Constants.CONNECTION_URI;
import static fr.diginamic.jdbc.recensement.Constants.PASSWORD;
import static fr.diginamic.jdbc.recensement.Constants.USER;

public class CityDao implements Dao<City> {
  private static final Dao<Region> regionDao = new RegionDao();
  private static final Dao<Department> departmentDao = new DepartmentDao();

  @Override
  public int[] bulkInsert(List<City> cities) throws SQLException {
    String query = "INSERT IGNORE INTO cities (name,code,population,departmentId,regionId) VALUES(?,?,?,?,?)";
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement(query);
    ) {
      long start = System.currentTimeMillis();
      insertRegionsAndDepartments(cities);

      var departmentMap = departmentDao.reduce();
      var regionMap = regionDao.reduce();

      for (City city : cities) {
        int departmentId = departmentMap.getOrDefault(city.department().code(), -1);
        int regionId = regionMap.getOrDefault(city.region().name(), -1);

        if (departmentId != -1 && regionId != -1) {
          statement.setString(1, city.name());
          statement.setInt(2, city.code());
          statement.setLong(3, city.population());
          statement.setInt(4, departmentId);
          statement.setInt(5, regionId);
          statement.addBatch();
        }
      }

      System.out.println("Time: " + (System.currentTimeMillis() - start));

      return statement.executeBatch();

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static void insertRegionsAndDepartments(List<City> cities) throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(10);

    var insertRegions = new Callable<int[]>() {
      @Override
      public int[] call() throws SQLException {
        List<Region> regions = cities.stream()
            .map(city -> city.region())
            .distinct()
            .toList();

        return regionDao.bulkInsert(regions);
      }
    };

    var insertDepartments = new Callable<int[]>() {

      @Override
      public int[] call() throws SQLException {
        List<Department> departments = cities.stream()
            .map(city -> city.department())
            .distinct()
            .toList();

        return departmentDao.bulkInsert(departments);
      }
    };

    executor.invokeAll(List.of(insertRegions, insertDepartments));
    executor.shutdown();
  }

  @Override
  public List<City> find() {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public Map<String, Integer> reduce() {
    throw new RuntimeException("Not implemented");
  }
}
