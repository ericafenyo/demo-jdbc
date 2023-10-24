package fr.diginamic.jdbc.recensement.model;

public class Department {
  private int id;
  private String code;

  public Department(String code) {
    this.code = code;
  }

  public Department(int id, String code) {
    this.id = id;
    this.code = code;
  }

  public int id() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String code() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
