package fr.diginamic.jdbc.recensement.model;

public class City {
  private int id = -1;
  private String name;
  private int code;
  private long population;
  private Region region;
  private Department department;

  public City(int id, String name, int code, long population, Region region, Department department) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.population = population;
    this.region = region;
    this.department = department;
  }

  public City(String name, int code, long population, Region region, Department department) {
    this.name = name;
    this.code = code;
    this.population = population;
    this.region = region;
    this.department = department;
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

  public long population() {
    return population;
  }

  public void setPopulation(long population) {
    this.population = population;
  }

  public Region region() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

  public Department department() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }
}
