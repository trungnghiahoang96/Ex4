import Util.FileParsing.FileParser;
import Util.IO.IO;
import Util.InfoLoader.FileInfoLoader;
import controller.Query;
import model.author.Author;
import model.author.AuthorInfo;
import model.book.Book;
import model.book.BookInfo;


import java.util.ArrayList;
import java.util.List;


public class Main {

    private static List<Book> books = new ArrayList<>();
    private static List<Author> authors = new ArrayList<>();

    private static AuthorInfo authorInfo;
    private static BookInfo bookInfo;

    public static void main(String[] args) {

        loadData();

        start();
//        example name of book: Harry Potter and the Chamber of Secrets
//        example name of author: J.K. Rowling


    }


    public static void start() {
        while (true) {

            displayChoices();

            try {
                int choice = IO.inputInt();

                if (isTerminationChoice(choice)) {
                    System.out.println("Program Exiting..... ");
                    break;
                } else {
                    executeChoice(choice);
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a number! Enter another number option please!\n");

            }

        }
    }

    public static void displayChoices() {
        System.out.println("Welcome to the magic library:");
        System.out.println("Menu");
        System.out.println("0 - Quit\n" +
                "1 - List all books written by an author (J.K. Rowling)\n" +
                "2 - Show info of author/ authors given name of book (Harry Potter and the Chamber of Secrets)\n" +
                "3 - Number of books written by author (J.K. Rowling)\n" +
                "4 - Given a date, what are the books written in that year\n" +
                "5 - Find the top N prolific authors\n" +
                "6 - Find the top N highest rating books\n");
    }

    public static boolean isTerminationChoice(int i) {
        return i == 0;
    }

    public static void executeChoice(int choice) {

        switch (choice) {
            case 1:
                Query.showAllBooksOfAuthorWithOrder(authorInfo);
                break;
            case 2:
                Query.authorsInfoGivenBook(books, authorInfo);
                break;
            case 3:
                Query.numberOfBooksByAuthor(authorInfo);
                break;
            case 4:
                Query.listBooksGivenDate(books);
                break;
            case 5:
                Query.showMostProlificAuthors(authors);
                break;
            case 6:
                Query.showHighestRatingBooks(books);
                break;
            default:
                String warning = "";

                warning += "XXXXXXXXXXXXXXXXXXXXXXXX\n";
                warning += "Invalid option!! Enter valid option:\n";
                warning += "XXXXXXXXXXXXXXXXXXXXXXXX\n";

                System.out.println(warning);

        }
    }

    private static void loadData() {
        FileInfoLoader fileInfoLoader = new FileInfoLoader();


        authorInfo = fileInfoLoader.loadAuthorInfo();

        bookInfo = fileInfoLoader.loadBookInfo();

        books = new ArrayList<>(bookInfo.getIdBookMap().values());
        authors = new ArrayList<>(authorInfo.getNameAuthorMap().values());


    }
}



