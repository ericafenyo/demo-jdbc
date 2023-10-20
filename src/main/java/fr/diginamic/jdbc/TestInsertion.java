package fr.diginamic.jdbc;

import java.sql.*;

public class TestInsertion {
  public static void main(String[] args) {
    try {
      // Create an SQL connection using MySQL Driver
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
      System.out.println(connection);

      // Insérez un nouveau fournisseur appelé « La Maison de la Peinture ».

      PreparedStatement statement = connection.prepareStatement("INSERT INTO FOURNISSEUR (ID, NOM) VALUES(?,?)");
      statement.setInt(1, 5);
      statement.setString(2, "La Maison de la Peinture");
      statement.execute();
      statement.close();
      connection.close();

    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
}
