package model.book;

import model.author.Author;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private final int id;
    private final String title;
    private final double publicationYear;
    private final double averageRating;
    private final List<String> authorsOfBook;



    public Book(int id, String title, double publicationYear, double averageRating, List<String> authorsOfBook) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.averageRating = averageRating;
        this.authorsOfBook = authorsOfBook;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPublicationYear() {
        return publicationYear;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public List<String> getAuthorsOfBook() {
        return authorsOfBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", averageRating=" + averageRating +
                ", authorsOfBook=" + authorsOfBook +
                '}';
    }
}
