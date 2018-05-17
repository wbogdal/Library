package pl.atos.model;

public class Reader {
  private String name;
  private String surname;


  public Reader(String name, String surname) {
    this.name = name;
    this.surname = surname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public String toString() {
    return "Reader{" +
        "name = '" + name + '\'' +
        ", surname = '" + surname + '\'' +
        '}';
  }
}
