package pl.atos.logic;

import pl.atos.db.InMemoryDatabase;
import pl.atos.model.Reader;
import pl.atos.model.Status;

public class BookStatus {

  private final InMemoryDatabase inMemoryDatabase;

  public BookStatus(InMemoryDatabase inMemoryDatabase) {
    this.inMemoryDatabase = inMemoryDatabase;
  }

  public void rentBook(int id, Reader reader) {
    if (inMemoryDatabase.getBookById(id).getStatus().equals(Status.available)) {
      inMemoryDatabase.getBookById(id).setStatus(Status.lent);
      inMemoryDatabase.getBookById(id).setReader(reader);
    }
  }

  public void returnBook(int id, Reader reader) {
    if (inMemoryDatabase.getBookById(id).getStatus().equals(Status.lent)) {
      inMemoryDatabase.getBookById(id).setStatus(Status.available);
      inMemoryDatabase.getBookById(id).setReader(null);
    }
  }
}
