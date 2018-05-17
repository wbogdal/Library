package pl.atos.db;

import pl.atos.logic.calculator.AvailableCalculator;
import pl.atos.logic.calculator.CalculatorVisitor;
import pl.atos.logic.calculator.LentCalculator;
import pl.atos.model.Author;
import pl.atos.model.Book;
import pl.atos.model.Status;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;

public class InMemoryDatabase implements IdGenerator {

  private static final AtomicInteger counter = new AtomicInteger(1);
  private static List<Book> books = new ArrayList<>();

  public void saveBook(Book book) {
    books.add(book);
  }

  public void removeBook(int id) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getId() == id && books.get(i).getStatus() == Status.available) {
        books.remove(i);
      }
    }
  }

  public List<Book> getBooks() {
    return books;
  }

  public int generate() {
    return counter.getAndIncrement();
  }

  public int getLastId() {
    return getBooks().get(books.size() - 1).getId();
  }

  public Book getBookById(int id) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getId() == id) {
        return books.get(i);
      }
    }
    return null;
  }

  public List<Book> getBooksByTitle(String title) {
    List<Book> booksByTitle = new ArrayList<>();
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getTitle().equals(title)) {
        booksByTitle.add(books.get(i));
      }
    }
    return booksByTitle;
  }

  public List<Book> getBooksByAuthor(Author author) {
    List<Book> booksByAuthor = new ArrayList<>();
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getAuthor().equals(author)) {
        booksByAuthor.add(books.get(i));
      }
    }
    return booksByAuthor;
  }

  public List<Book> getBooksByYear(Year year) {
    List<Book> booksByYear = new ArrayList<>();
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getYear().equals(year)) {
        booksByYear.add(books.get(i));
      }
    }
    return booksByYear;
  }

  private void getBookNumbers(){
    List <String> titles = getDistinctBooks();
    for (String title : titles){
      System.out.println(title + ": available books number " + accept(new AvailableCalculator(),books,title)
          + " lent books number " + accept(new LentCalculator(),books,title));
    }
  }

  private static List<String> getDistinctBooks() {
    List<String> distinctBooks = new ArrayList<>();
    books.stream().filter(distinctByKey(b -> b.getTitle()))
        .forEach(b -> distinctBooks.add((b.getTitle())));
    return distinctBooks;
  }

  private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Map<Object,Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  public int accept(CalculatorVisitor visitor, List<Book> books, String title) {
    List<Book> actual = books;
    int sum = 0;
    for (Book book : actual) {
      sum = sum + (visitor.visit(book,title));
    }
    return sum;
  }

}
