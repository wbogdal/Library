package pl.atos.logic;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pl.atos.db.InMemoryDatabase;
import pl.atos.model.Author;
import pl.atos.model.Book;
import pl.atos.model.Reader;
import pl.atos.model.Status;

import java.time.Year;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookStatusTest {

  InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
  BookFactory bookFactory = new BookFactory(inMemoryDatabase);
  BookStatus bookStatus = new BookStatus(inMemoryDatabase);


  @Test
  public void ShouldRentBook() throws Exception {
    //given
    Book book = bookFactory.create("Przygody Tomka Sawyera", Year.of(1999), new Author("Mark", "Twain"));
    inMemoryDatabase.saveBook(book);
    Reader reader = new Reader ("Krzysztof","Krawczyk");
    Status expected = Status.lent;

    //when
    bookStatus.rentBook(1,reader);

    //then
    assertEquals(book.getStatus(),expected);
  }

  @Test
  public void ShouldReturnBook() throws Exception {
    //given
    Book book = bookFactory.create("Przygody Tomka Sawyera", Year.of(1999), new Author("Mark", "Twain"));
    inMemoryDatabase.saveBook(book);
    Reader reader = new Reader ("Jan","Nowak");
    book.setStatus(Status.lent);
    Status expected = Status.available;

    //when
    bookStatus.returnBook(2,reader);

    //then
    assertEquals(book.getStatus(),expected);
  }

}