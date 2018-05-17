package pl.atos.logic;

import pl.atos.db.IdGenerator;
import pl.atos.model.Author;
import pl.atos.model.Book;

import java.time.Year;

public class BookFactory {
  private final IdGenerator idGenerator;

  public BookFactory(IdGenerator idGenerator) {
    this.idGenerator = idGenerator;
  }

  public Book create(String title, Year year, Author author) {
      int id = idGenerator.generate();
      return new Book(id, title, year, author);
    }
}
