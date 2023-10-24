package fr.diginamic.jdbc.recensement.model;

public class Region {
  private int id = -1;
  private String name;
  private int code;

  public Region(String name, int code) {
    this.name = name;
    this.code = code;
  }

  public Region(int id, String name, int code) {
    this.id = id;
    this.name = name;
    this.code = code;
  }

  public int id() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String name() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int code() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
