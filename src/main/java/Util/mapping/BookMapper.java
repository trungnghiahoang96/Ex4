package Util.mapping;

import Util.FileParsing.FileParser;
import model.author.Author;
import model.book.Book;
import wrappers.BookMap;


import java.util.Arrays;
import java.util.List;
import java.util.Map;


public final class BookMapper {

    private final BookMap idBookMap;

    public BookMapper(FileParser fileParser) {
        this.idBookMap = bookMap(fileParser);
    }


    /**
     * @param fileParser fileParse can return List of all raw lines
     * @return Map of pair <ID, Book>
     */
    private BookMap bookMap(FileParser fileParser) {
        BookMap tempIdBookMap = new BookMap();

        List<List<String>> bookEntriesList = fileParser.getRawList();

        for (List<String> bookEntry : bookEntriesList) {
            Book book = book(bookEntry);
            tempIdBookMap.put(book.getId(), book);
        }

        return tempIdBookMap;

    }

    /**
     * @param bookEntry is a line from CSV file after split
     * @return a new book with information get from a CSV line
     */
    public Book book(List<String> bookEntry) {

        int id = Integer.parseInt(bookEntry.get(0));
        String title = bookEntry.get(8);
        double publicationYear = Double.parseDouble(bookEntry.get(7));
        double averageRating = Double.parseDouble(bookEntry.get(10));

        List<String> authorsOfBook = Arrays.asList(bookEntry.get(6).split(","));


        return new Book(id, title, publicationYear, averageRating, authorsOfBook);

    }

    public Map<Integer, Book> getIdBookMap() {
        return idBookMap;
    }
}
