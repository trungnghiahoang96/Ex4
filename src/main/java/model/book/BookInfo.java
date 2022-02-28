package model.book;

import model.author.Author;
import wrappers.BookMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookInfo {
    private final Map<Integer, Book> idBookMap;

    public BookInfo(Map<Integer, Book> idBookMap) {
        this.idBookMap = idBookMap;
    }


    /**
     * get Book given ID
     *
     * @param ID ID of book
     */
    public Book getBook(int ID) {
        Book book = idBookMap.getOrDefault(ID, null);

        if (book == null)
            throw new IllegalArgumentException("Book with given ID does not exist : " + ID);

        return book;
    }


    /**
     * @param ID
     * ID of book
     * @return title given ID of book
     */
    public String getTitle(int ID) {
        Book book = idBookMap.getOrDefault(ID, null);

        if (book == null)
            throw new IllegalArgumentException("book does not exist");

        return book.getTitle();
    }


    /**
     * @param ID
     * ID of book
     * @return list of books' Author given ID
     */
    public List<String> getAuthors(int ID) {
        Book book = idBookMap.getOrDefault(ID, null);
        List<String> authorsList = new ArrayList<>();

        if (book == null) {
            return authorsList;
        } else {
            List<String> authors = book.getAuthorsOfBook();
            authorsList.addAll(authors);
        }
        return authorsList;
    }

    public BookMap getIdBookMap() {
        return (BookMap) idBookMap;
    }
}
