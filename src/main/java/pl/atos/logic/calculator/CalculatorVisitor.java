package pl.atos.logic.calculator;

import pl.atos.model.Book;

public interface CalculatorVisitor {
  public int visit(Book book, String title);
}
