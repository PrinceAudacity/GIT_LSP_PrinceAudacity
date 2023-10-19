package org.howard.edu.lsp.midterm.problem1;

public class Book {
  private String title;
  private String author;
  private int year;

  public Book(String title, String author, int year) {
    this.title = title;
    this.author = author;
    this.year = year;
  }

  @Override
  public String toString() {
    return "Title: " + this.title + ", Author: " + this.author + ", Year: " + this.year;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Book otherBook = (Book) obj;

    return title.equals(otherBook.title) && author.equals(otherBook.author);
  }
}
