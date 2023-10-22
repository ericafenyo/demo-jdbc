package fr.diginamic.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestSelect {
  private static final String CONNECTION_URI = "jdbc:mysql://localhost:3306/compta";
  private static final String USER = "root";
  private static final String PASSWORD = "password";

  public static void main(String[] args) {
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        Statement statement = connection.createStatement();
    ) {
      try (ResultSet cursor = statement.executeQuery("SELECT * FROM FOURNISSEUR");) {
        List<String> result = new ArrayList<>();

        while (cursor.next()) {
          int id = cursor.getInt("ID");
          String name = cursor.getString("NOM");
          result.add(String.format("%d. %s", id, name));
        }

        for (String string : result) {
          System.out.println(string);
        }

      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
}
