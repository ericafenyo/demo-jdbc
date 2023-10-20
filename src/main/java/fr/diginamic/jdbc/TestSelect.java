package fr.diginamic.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestSelect {
  public static void main(String[] args) {
    PreparedStatement statement;
    Connection connection;
    try {
      // Create an SQL connection using MySQL Driver
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
      System.out.println(connection);

      statement = connection.prepareStatement("SELECT * FROM FOURNISSEUR");
      ResultSet cursor = statement.executeQuery();

      List<String> result = new ArrayList<>();

      while (cursor.next()) {
        int id = cursor.getInt("ID");
        String name = cursor.getString("NOM");
        result.add(String.format("%d. %s", id, name));
      }

      for (String string : result) {
        System.out.println(string);
      }

      statement.close();
      connection.close();

    } catch (SQLException exception) {
      exception.printStackTrace();
    } finally {

    }
  }
}
