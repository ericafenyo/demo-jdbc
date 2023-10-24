package fr.diginamic.jdbc.recensement;

import fr.diginamic.jdbc.recensement.dao.CityDao;
import fr.diginamic.jdbc.recensement.dao.Dao;
import fr.diginamic.jdbc.recensement.model.City;

import java.sql.SQLException;

public class Application {
  public static void main(String[] args) throws SQLException {
    Census census = Census.create();
    Dao<City> cityDao = new CityDao();

    cityDao.bulkInsert(census.getCities());
  }
}
