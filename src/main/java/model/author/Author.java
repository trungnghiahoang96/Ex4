package model.author;

import model.book.Book;

import java.util.ArrayList;
import java.util.List;

public final class Author {


    private final String name;
    private final List<Book> booksOfAuthor;


    public Author(String name) {
        this.name = name;
        this.booksOfAuthor = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooksOfAuthor() {
        return booksOfAuthor;
    }

    public int getBookCount() {
        return booksOfAuthor.size();
    }

    public void addBook(Book book) {
        booksOfAuthor.add(book);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", booksOfAuthor=" + booksOfAuthor +
                '}';
    }
}
