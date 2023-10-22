package fr.diginamic.jdbc;

import java.sql.*;

public class TestInsertion {
  private static final String CONNECTION_URI = "jdbc:mysql://localhost:3306/compta";
  private static final String USER = "root";
  private static final String PASSWORD = "password";

  public static void main(String[] args) {
    try (
        Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO FOURNISSEUR (ID, NOM) VALUES(?,?)");
    ) {
      statement.setInt(1, 5);
      statement.setString(2, "La Maison de la Peinture");
      statement.execute();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
}
