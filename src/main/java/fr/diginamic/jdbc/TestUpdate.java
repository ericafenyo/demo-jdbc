package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdate {
  public static void main(String[] args) throws SQLException {
    // Create an SQL connection using MySQL Driver
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
    System.out.println(connection);

    PreparedStatement statement = connection.prepareStatement("UPDATE FOURNISSEUR SET NOM=? WHERE ID=?");
    statement.setString(1, "La Maison des Peintures");
    statement.setInt(2, 5);
    statement.execute();
    statement.close();
    connection.close();
  }
}
