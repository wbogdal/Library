package pl.atos.logic.calculator;

import pl.atos.model.Book;
import pl.atos.model.Status;

public class AvailableCalculator implements CalculatorVisitor {

  @Override
  public int visit(Book book, String title) {
    int sum = 0;
    if (book.getTitle().equals(title) && book.getStatus()== Status.available) {
      sum = 1;
    }
    return sum;
  }
}
