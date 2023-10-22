package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDelete {
  private static final String CONNECTION_URI = "jdbc:mysql://localhost:3306/compta";
  private static final String USER = "root";
  private static final String PASSWORD = "password";

  public static void main(String[] args) {
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("DELETE FROM FOURNISSEUR WHERE ID=?");
    ) {
      statement.setInt(1, 5);
      statement.execute();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
}
