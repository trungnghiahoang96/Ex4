package model.book;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book bookTest;

    @BeforeEach
    void Setup() {
        int id = 1;
        String title = "Harry Potter and the Chamber of Secrets";
        double publicationYear = 1998.0;
        double averageRating = 4.37;
        List<String> authorsOfBook = Arrays.asList("J.K. Rowling", "Mary GrandPré");

        bookTest = new Book(id, title, publicationYear, averageRating, authorsOfBook);
    }

    @Test
    void getTitle() {
        assertEquals("Harry Potter and the Chamber of Secrets", bookTest.getTitle());
    }

    @Test
    void getPublicationYear() {
        assertEquals(1998.0, bookTest.getPublicationYear());
    }

    @Test
    void getAverageRating() {
        assertEquals(4.37, bookTest.getAverageRating());

    }

    @Test
    void getAuthorsOfBook() {
        List<String> testList = Arrays.asList("J.K. Rowling", "Mary GrandPré");
        assertThat(testList).hasSameHashCodeAs(bookTest.getAuthorsOfBook());

    }



}