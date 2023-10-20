package fr.diginamic;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {
  public static void main(String[] args) {
    try {
      // Create an SQL connection using MySQL Driver
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "password");

      System.out.println(connection);

    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
}
