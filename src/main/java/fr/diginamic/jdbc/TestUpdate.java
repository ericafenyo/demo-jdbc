package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdate {
  private static final String CONNECTION_URI = "jdbc:mysql://localhost:3306/compta";
  private static final String USER = "root";
  private static final String PASSWORD = "password";

  public static void main(String[] args) {
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("UPDATE FOURNISSEUR SET NOM=? WHERE ID=?");
    ) {
      statement.setString(1, "La Maison des Peintures");
      statement.setInt(2, 5);
      statement.execute();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
}
