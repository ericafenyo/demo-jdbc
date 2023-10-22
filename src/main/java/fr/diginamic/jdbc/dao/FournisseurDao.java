package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface FournisseurDao {

  List<Fournisseur> extraire();

  void insert(Fournisseur fournisseur);

  int update(String ancienNom, String nouveauNom);

  boolean delete(Fournisseur fournisseur);


  class FournisseurDaoJdbc implements FournisseurDao {
    private static final String CONNECTION_URI = "jdbc:mysql://localhost:3306/compta";
    private static final String USER = "root";
    private static final String PASSWORD = "password";


    @Override
    public List<Fournisseur> extraire() {
      try (
          Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
          Statement statement = connection.createStatement()
      ) {

        try (ResultSet result = statement.executeQuery("SELECT * FROM FOURNISSEUR");) {
          List<Fournisseur> accumulator = new ArrayList<>();
          while (result.next()) {
            int id = result.getInt("ID");
            String nom = result.getString("NOM");
            accumulator.add(new Fournisseur(id, nom));
          }

          return accumulator;
        }
      } catch (SQLException exception) {
        exception.printStackTrace();
        return new ArrayList<>();
      }
    }

    @Override
    public void insert(Fournisseur fournisseur) {
      try (
          Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
          PreparedStatement statement = connection.prepareStatement("INSERT INTO FOURNISSEUR (ID, NOM) VALUES(?,?)")
      ) {
        statement.setInt(1, fournisseur.id());
        statement.setString(2, fournisseur.nom());
        statement.execute();
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
      try (
          Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
          PreparedStatement statement = connection.prepareStatement("UPDATE FOURNISSEUR SET NOM=? WHERE NOM=?")
      ) {
        statement.setString(1, nouveauNom);
        statement.setString(2, ancienNom);
        return statement.executeUpdate();
      } catch (SQLException exception) {
        exception.printStackTrace();
        return 0;
      }
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
      try (
          Connection connection = DriverManager.getConnection(CONNECTION_URI, USER, PASSWORD);
          PreparedStatement statement = connection.prepareStatement("DELETE FROM FOURNISSEUR WHERE ID=?")
      ) {
        statement.setInt(1, fournisseur.id());
        return statement.execute();
      } catch (SQLException exception) {
        exception.printStackTrace();
        return false;
      }
    }
  }
}
