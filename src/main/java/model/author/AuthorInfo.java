package model.author;

import model.book.Book;
import wrappers.AuthorMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AuthorInfo {
    private final Map<String, Author> nameAuthorMap;

    public AuthorInfo(Map<String, Author> nameAuthorMap) {
        this.nameAuthorMap = nameAuthorMap;
    }


    /**
     * get Author given name
     *
     * @param name name of book
     */
    public Optional<Author> getAuthor(String name) {
        Author author = nameAuthorMap.getOrDefault(name, null);
        if (author == null)
            throw new IllegalArgumentException("Author with given name does not exist : " + name);

        return Optional.of(author);
    }


    /**
     * @param name  name of author
     * @return book written by this Author (given his/ her name)
     */
    public List<Book> getBooksOfAuthor(String name) {
        Author author = nameAuthorMap.getOrDefault(name, null);

        List<Book> listOfBook = new ArrayList<>();

        if (author == null) {
            return listOfBook;
        } else {
            List<Book> booksOfAuthor = author.getBooksOfAuthor();
            listOfBook.addAll(booksOfAuthor);
        }

        return listOfBook;
    }

    public AuthorMap getNameAuthorMap() {
        return (AuthorMap) nameAuthorMap;
    }
}
