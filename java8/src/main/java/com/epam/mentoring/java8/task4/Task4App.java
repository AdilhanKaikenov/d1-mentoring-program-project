package com.epam.mentoring.java8.task4;

import com.epam.mentoring.java8.task4.model.Author;
import com.epam.mentoring.java8.task4.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Kaikenov Adilhan
 **/
public class Task4App {

    private static final Logger log = LoggerFactory.getLogger(Task4App.class);

    public static void main(String[] args) {

//        Create two arrays: Author[] authors and Book[] books.
//        Their elements must use elements from the neighboring array.
        Author[] authors = {
                new Author("Oscar", 30, Collections.EMPTY_LIST),
                new Author("Freddie", 45, Collections.EMPTY_LIST),
                new Author("Harry", 33, Collections.EMPTY_LIST),
                new Author("Jack", 32, Collections.EMPTY_LIST),
                new Author("Lily", 44, Collections.EMPTY_LIST)
        };

        Book[] books = {
                new Book("Book1", Arrays.asList(authors[0], authors[2]), 250),
                new Book("Book2", Arrays.asList(authors[1], authors[2]), 350),
                new Book("Book3", Arrays.asList(authors[2]), 550),
                new Book("Book4", Arrays.asList(authors[3], authors[4]), 650),
                new Book("Book5", Arrays.asList(authors[4]), 750),
                new Book("Book6", Arrays.asList(authors[2]), 350),
                new Book("Book7", Arrays.asList(authors[3], authors[0]), 250),
                new Book("Book8", Arrays.asList(authors[4]), 550),
                new Book("Book9", Arrays.asList(authors[0]), 450),
                new Book("Book10", Arrays.asList(authors[2], authors[0]), 250),
                new Book("Book11", Arrays.asList(authors[3]), 350),
                new Book("Book12", Arrays.asList(authors[4], authors[0]), 950),
                new Book("Book13", Arrays.asList(authors[2]), 1250),
                new Book("Book14", Arrays.asList(authors[1]), 350),
                new Book("Book15", Arrays.asList(authors[3], authors[4]), 150)
        };

//        Create a stream of books and then:
        Supplier<Stream<Book>> booksSupplier = new ArrayList<>(Arrays.asList(books))::stream;

//        I. check if some book(s) have more than 200 pages; (a)
        final int pageNumber = 200;
        if (booksSupplier.get().anyMatch(book -> book.getNumberOfPages() > pageNumber)) {
            System.out.println("Some book(s) have more than 200 pages");
        }
//        I. check if all books have more than 200 pages; (b)
        if (booksSupplier.get().allMatch(book -> book.getNumberOfPages() > pageNumber)) {
            System.out.println("All books have more than 200 pages");
        }

//        II. find the books with max and min number of pages;
        Optional<Book> max = booksSupplier.get()
                .max((book1, book2) -> Integer.compare(book1.getNumberOfPages(), book2.getNumberOfPages()));
        Optional<Book> min = booksSupplier.get()
                .min(Comparator.comparingInt(Book::getNumberOfPages)); // shorter version

//        III. filter books with only single author;
        List<Book> singleAuthorBooks = booksSupplier.get()
                .filter(book -> book.getAuthors().size() == 1)
                .collect(Collectors.toList());

//        IV. sort the books by number of pages/title;
        List<Book> sortedBooks = booksSupplier.get()
                .sorted(Comparator.comparingInt(Book::getNumberOfPages))
                .collect(Collectors.toList());

//        V. get list of all titles;
        List<String> bookTitles = booksSupplier.get()
                .map(Book::getTitle)
                .collect(Collectors.toList());

//        VI. print them using forEach method;
        bookTitles.forEach(System.out::println);

//        VII. get distinct list of all authors
        List<Author> authorList = booksSupplier.get()
                .map(Book::getAuthors)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

//        Use peek method for debugging intermediate streams during execution the previous task.
        List<Author> list = booksSupplier.get()
                .map(Book::getAuthors)
                .peek(aList -> log.debug("Book author list size = {}", aList.size()))
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

//        Use the Optional type for determining the title of the biggest book of some author.
        Optional<String> optional = booksSupplier.get()
                .filter(book -> book.getAuthors().stream().anyMatch(author -> author.getName().equals("Harry")))
                .max(Comparator.comparing(Book::getNumberOfPages))
                .map(Book::getTitle);

        optional.ifPresent(System.out::println);

//        Use parallel stream with subtask #3.

        /*
        Streams give us the flexibility to iterate over the list in a parallel pattern and can give the total in quick
        fashion. Stream implementation in Java is by default sequential unless until it is explicitly mentioned in parallel.
        When a stream executes in parallel, the Java runtime partitions the stream into multiple sub-streams.
        Aggregate operations iterate over and process these sub-streams in parallel and then combine the results.

        The performance may not improve when using multiple threads.
        For example, when we use something in stream which is synchronized.
         */
        ArrayList<Book> bList = new ArrayList<>(Arrays.asList(books));
        List<Book> singleAuthorBooks2 = bList.parallelStream()
                .filter(book -> book.getAuthors().size() == 1)
                .collect(Collectors.toList());


    }
}
