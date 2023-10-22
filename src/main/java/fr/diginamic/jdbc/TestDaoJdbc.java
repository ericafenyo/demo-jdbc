package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDao;
import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.Objects;
import java.util.Optional;

public class TestDaoJdbc {
  public static void main(String[] args) {
    FournisseurDao dao = new FournisseurDao.FournisseurDaoJdbc();

    // insérer un fournisseur appelé « France de matériaux »
    Fournisseur fournisseur = new Fournisseur(58, "France de matériaux");
    System.out.println("Inserting Fournisseur" );
    dao.insert(fournisseur);

    // Extraire la liste des fournisseurs de la base et les afficher
    dao.extraire().forEach(element -> System.out.println(element));

    System.out.println("--------------------------------------" );
    // modifier le fournisseur appelé « France de matériaux » en « France matériaux »
    System.out.println("Modifying Fournisseur" );
    dao.update("France de matériaux", "France matériaux");

    //  extraire la liste des fournisseurs de la base de données et les afficher à nouveau
    dao.extraire().forEach(element -> System.out.println(element));

    // supprimer le fournisseur « France matériaux »
    System.out.println("Deleting Fournisseur" );
    Optional<Fournisseur> result = dao.extraire().stream()
        .filter(element -> element.nom().equals("France matériaux"))
        .findFirst();

    if (result.isPresent()) {
      dao.delete(result.get());
    }

    // extraire la liste des fournisseurs de la base de données et les afficher à nouveau
    dao.extraire().forEach(element -> System.out.println(element));
  }
}
