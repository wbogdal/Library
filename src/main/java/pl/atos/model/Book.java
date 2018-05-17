package pl.atos.model;

import java.time.Year;

public class Book {
  private int id;
  private String title;
  private Year year;
  private Author author;
  private Status status;
  private Reader reader;

  public Book () {
  }

  public Book(int id, String title, Year year, Author author) {
    this.id = id;
    this.title = title;
    this.year = year;
    this.author = author;
    status = Status.available;
    reader = null;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Year getYear() {
    return year;
  }

  public void setYear(Year year) {
    this.year = year;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Reader getReader() {
    return reader;
  }

  public void setReader(Reader reader) {
    this.reader = reader;
  }

  @Override
  public String toString() {
    if (getStatus().equals(Status.available)) {
      return "Book{" +
          "id = " + id +
          ", title = '" + title + '\'' +
          ", year = " + year +
          ", author = " + author +
          ", status = " + status +
          '}';
    } else {
      return "Book{" +
          "id = " + id +
          ", title = '" + title + '\'' +
          ", year = " + year +
          ", author = " + author +
          ", status = " + status +
          ", reader = " + reader +
          '}';
    }
  }
}
