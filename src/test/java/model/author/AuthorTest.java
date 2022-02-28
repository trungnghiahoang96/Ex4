package model.author;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {
    private Author authorTest;

    @BeforeEach
    void Setup() {
        authorTest = new Author("Kafka");
    }

    @Test
    void getRightName() {
        assertEquals("Kafka", authorTest.getName());
    }

}