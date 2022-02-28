package controller;

import Util.IO.IO;
import model.author.Author;
import model.author.AuthorInfo;
import model.book.Book;

import java.text.ParseException;
import java.time.DateTimeException;
import java.util.*;

import java.util.stream.Collectors;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Query {

    public static void showAllBooksOfAuthorWithOrder(AuthorInfo authorInfo) {
        Scanner sc = new Scanner(System.in);


        List<Book> booksOfAuthor = new ArrayList<>();


        System.out.println("Please enter the author you want to search ?");
        String authorName = sc.nextLine();

        try {
            Optional<Author> chosenAuthor = authorInfo.getAuthor(authorName);
            if (chosenAuthor.isPresent()) {
                booksOfAuthor = chosenAuthor.get().getBooksOfAuthor();
                while (true) {
                    System.out.println("Enter sort type: ");

                    System.out.println("0 - Quit this mode!\n" +
                            "1 - Alphabetical asc\n" +
                            "2 - Alphabetical desc,\n" +
                            "3 - Publication date asc\n" +
                            "4 - Publication date desc\n" +
                            "5 - Rating asc\n" +
                            "6 - Rating desc\n");


                    int choice = IO.inputInt();
                    if (choice == 0) {
                        System.out.println("Exiting this mode .... ");
                        break;
                    } else {
                        List<Book> sortedBooks = new ArrayList<>();
                        switch (choice) {
                            case 1:
                                sortedBooks = booksOfAuthor.stream()
                                        .sorted(Comparator.comparing(Book::getTitle))
                                        .collect(Collectors.toList());
                                break;
                            case 2:
                                sortedBooks = booksOfAuthor.stream()
                                        .sorted(Comparator.comparing(Book::getTitle).reversed())
                                        .collect(Collectors.toList());
                                break;
                            case 3:
                                sortedBooks = booksOfAuthor.stream()
                                        .sorted(Comparator.comparing(Book::getPublicationYear))
                                        .collect(Collectors.toList());
                                break;
                            case 4:
                                sortedBooks = booksOfAuthor.stream()
                                        .sorted(Comparator.comparing(Book::getPublicationYear).reversed())
                                        .collect(Collectors.toList());
                                break;
                            case 5:
                                sortedBooks = booksOfAuthor.stream()
                                        .sorted(Comparator.comparing(Book::getAverageRating))
                                        .collect(Collectors.toList());
                                break;
                            case 6:
                                sortedBooks = booksOfAuthor.stream()
                                        .sorted(Comparator.comparing(Book::getAverageRating).reversed())
                                        .collect(Collectors.toList());
                                break;
                            default:
                                String warning = "";
                                warning += "Invalid option!! Enter valid option:\n";
                                System.out.println(warning);
                        }
                        sortedBooks.forEach(System.out::println);
                        System.out.println();
                    }
                }


            }
        } catch (IllegalArgumentException e) {
            System.out.println("Not found! \n");
        }


    }

    public static void authorsInfoGivenBook(List<Book> books, AuthorInfo authorInfo) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Please enter the name of book you want to search ?");
        String bookName = sc.nextLine();

        Book chosenBook = books.stream()
                .filter(book -> book.getTitle().equals(bookName))
                .findAny().orElse(null);

        if (chosenBook != null) {
            for (String authorName : chosenBook.getAuthorsOfBook()) {
                System.out.println(authorInfo.getAuthor(authorName) + "\n");
            }
            System.out.println();
        } else {
            System.out.println("Not found!\n");
        }

    }

    public static void numberOfBooksByAuthor(AuthorInfo authorInfo) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the author you want to search ?");
        String authorName = sc.nextLine();

        try {
            Optional<Author> chosenAuthor = authorInfo.getAuthor(authorName);
            chosenAuthor.ifPresent(author -> System.out.println(authorName + " had " + author.getBooksOfAuthor().size() + " books in this library"));
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("Not found! \n");
        }
    }


    public static void listBooksGivenDate(List<Book> books) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println("Please enter the date with format dd/MM/yyyy: ");
        String date = sc.nextLine();
        try {
            LocalDate formattedDate = LocalDate.parse(date, DATE_PATTERN);
            List<Book> filteredBBook = books.stream()
                    .filter(book -> book.getPublicationYear() == formattedDate.getYear())
                    .collect(Collectors.toList());
            if (filteredBBook.isEmpty()) {
                System.out.println("No book in this library publicised this year! \n");
            } else {
                System.out.println("Books publicised that year are: ");
                filteredBBook.forEach(System.out::println);
                System.out.println();
            }
        } catch (DateTimeException e) {
            System.out.println("Wrong date format! \n");
        }

    }

    public static void showMostProlificAuthors(List<Author> authors) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter number of the most prolific authors you want to show: ");
        try {
            int topN = IO.inputInt();
            if (topN > 0) {
                List<Author> topNAuthor = authors.stream()
                        .sorted(Comparator.comparing(Author::getBookCount).reversed())
                        .limit(topN)
                        .collect(Collectors.toList());

                System.out.println("The most " + topN + " prolific authors are: ");
                topNAuthor.forEach(author -> System.out.println(author.getName() + " had " + author.getBookCount() + " books."));
                System.out.println();
            } else {
                System.out.println("N is positive integer! \n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a number! Enter another number please! \n");
        }
    }

    public static void showHighestRatingBooks(List<Book> books) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter number of the highest rating books you want to show: ");
        try {
            int topN = IO.inputInt();
            if (topN > 0) {
                List<Book> topNBook = books.stream()
                        .sorted(Comparator.comparing(Book::getAverageRating).reversed())
                        .limit(topN)
                        .collect(Collectors.toList());

                System.out.println("The " + topN + " highest rating books are: ");
                topNBook.forEach(book -> System.out.println(book.getTitle() + " with average rating: " + book.getAverageRating()));
                System.out.println();
            } else {
                System.out.println("N is positive integer! \n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a number! Enter another number please! \n");
        }
    }
}

