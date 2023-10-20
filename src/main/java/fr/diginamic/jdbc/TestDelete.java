package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDelete {
  public static void main(String[] args) {
    try {
      // Create an SQL connection using MySQL Driver
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
      System.out.println(connection);

      PreparedStatement statement = connection.prepareStatement("DELETE FROM FOURNISSEUR WHERE ID=?");
      statement.setInt(1, 5);
      statement.execute();
      statement.close();
      connection.close();

    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
}
