package Util.InfoLoader;

import Util.FileParsing.FileParser;
import Util.mapping.AuthorMapper;
import Util.mapping.BookMapper;
import model.author.AuthorInfo;
import model.book.BookInfo;

public class FileInfoLoader {
    String RESOURCES = "src/main/resources/";
    String pathOfBook = RESOURCES + "Books.csv";
    FileParser fileParser = new FileParser(pathOfBook, ";");

    public AuthorInfo loadAuthorInfo() {
//        BookInfo bookInfo = loadBookInfo();

        AuthorMapper authorMapper = new AuthorMapper(fileParser);

        return new AuthorInfo(authorMapper.getNameAuthorMap());
    }

    public BookInfo loadBookInfo() {
        BookMapper bookMapper = new BookMapper(fileParser);
        return new BookInfo(bookMapper.getIdBookMap());
    }
}
