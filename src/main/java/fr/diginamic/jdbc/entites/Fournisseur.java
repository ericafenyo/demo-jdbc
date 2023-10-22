package fr.diginamic.jdbc.entites;

public record Fournisseur(int id, String nom) {
  public Fournisseur copy(String nom) {
    return new Fournisseur(this.id, nom);
  }
}
