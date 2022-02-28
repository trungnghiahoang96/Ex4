package Util.mapping;

import Util.FileParsing.FileParser;
import model.author.Author;
import model.book.Book;
import wrappers.AuthorMap;

import java.util.List;
import java.util.Map;

public final class AuthorMapper {


    private final AuthorMap nameAuthorMap;

    public AuthorMapper(FileParser fileParser) {
        this.nameAuthorMap = authorMap(fileParser);
    }

    /**
     * @param fileParser can return all raw lines
     * @return Map of pair <authorName, author>
     */
    private AuthorMap authorMap(FileParser fileParser) {
        AuthorMap tempNameAuthorMap = new AuthorMap();

        List<List<String>> rawLines = fileParser.getRawList();

        BookMapper bookMapper = new BookMapper(fileParser);

        for (List<String> line : rawLines) {

            // book created from this line
            Book book = bookMapper.book(line);

            for (String authorName : line.get(6).split(",")) {
                Author author = tempNameAuthorMap.get(authorName);
                if (author != null) {
                    author.addBook(book);
                }
                else {
                    author = author(authorName);
                    author.addBook(book);
                    tempNameAuthorMap.put(author.getName(), author);
                }

            }
        }

        return tempNameAuthorMap;


    }

    private Author author(String authorName) {
        return new Author(authorName);

    }


    public Map<String, Author> getNameAuthorMap() {
        return nameAuthorMap;
    }

}
