package pl.atos.db;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import pl.atos.logic.BookFactory;
import pl.atos.model.Author;
import pl.atos.model.Book;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;


public class InMemoryDatabaseTest {


  @Test
  public void shouldSaveBook() throws Exception {
    //given
    final InMemoryDatabase database = mock(InMemoryDatabase.class);
    final Book book = mock(Book.class);
    //when
    database.saveBook(book);

    //then
    verify(database).saveBook(book);

  }

  @Test
  public void shouldRemoveBook() throws Exception {
    //given
    final InMemoryDatabase database = mock(InMemoryDatabase.class);
    final Book book = mock(Book.class);

    //when
    database.removeBook(book.getId());

    //then
    verify(database).removeBook(book.getId());
  }
  @Test
  public void shouldGetBooks() throws Exception {
    //given
    InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
    BookFactory bookFactory = new BookFactory(inMemoryDatabase);
    Book book = bookFactory.create("Przygody Tomka Sawyera", Year.of(1999), new Author("Mark", "Twain"));
    inMemoryDatabase.saveBook(book);
    List<Book> expected = new ArrayList<Book>();
    expected.add(book);

    //when
    List<Book> actual = inMemoryDatabase.getBooks();

    //then
    assertEquals(expected,actual);
  }

  @Test
  public void shouldGetLastId() throws Exception {
    //given
    InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
    BookFactory bookFactory = new BookFactory(inMemoryDatabase);
    Book book = bookFactory.create("Przygody Tomka Sawyera", Year.of(1999), new Author("Mark", "Twain"));
    inMemoryDatabase.saveBook(book);
    int expected = 1;

    //when
    int actual = inMemoryDatabase.getLastId();

    //then
    assertEquals(expected,actual);
  }

  @Test
  public void shouldGetBookById() throws Exception {
    //given
    InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
    BookFactory bookFactory = new BookFactory(inMemoryDatabase);
    Book book = bookFactory.create("Przygody Tomka Sawyera", Year.of(1999), new Author("Mark", "Twain"));
    inMemoryDatabase.saveBook(book);
    Book expected = book;

    //when
    Book actual = inMemoryDatabase.getBookById(1);

    //then
    assertEquals(expected,actual);
  }

  @Test
  public void shouldGetBooksByTitle() throws Exception {
    //given
    InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
    BookFactory bookFactory = new BookFactory(inMemoryDatabase);
    Book book = bookFactory.create("Pan Tadeusz", Year.of(1999), new Author("Adam", "Mickiewicz"));
    inMemoryDatabase.saveBook(book);
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(book);

    //when
    List<Book> actual = inMemoryDatabase.getBooksByTitle("Pan Tadeusz");

    //then
    assertEquals(expected,actual);
  }

  @Test
  public void shouldGetBooksByAuthor() throws Exception {
    //given
    InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
    BookFactory bookFactory = new BookFactory(inMemoryDatabase);
    Author author = new Author("Adam", "Mickiewicz");
    Book book = bookFactory.create("Pan Tadeusz", Year.of(1999), author);
    inMemoryDatabase.saveBook(book);
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(book);

    //when
    List<Book> actual = inMemoryDatabase.getBooksByAuthor(author);

    //then
    assertEquals(expected,actual);
  }

  @Test
  public void shouldGetBooksByYear() throws Exception {
    //given
    InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
    BookFactory bookFactory = new BookFactory(inMemoryDatabase);
    Author author = new Author("Adam", "Mickiewicz");
    Book book = bookFactory.create("Pan Tadeusz", Year.of(1999), author);
    inMemoryDatabase.saveBook(book);
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(book);

    //when
    List<Book> actual = inMemoryDatabase.getBooksByYear(Year.of(1999));

    //then
    assertEquals(expected,actual);
  }

}