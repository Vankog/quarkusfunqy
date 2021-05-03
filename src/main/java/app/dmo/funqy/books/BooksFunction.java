package app.dmo.funqy.books;

import io.quarkus.funqy.Funq;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class BooksFunction {

    @Inject
    BookRepository repository;

    @Funq
    public List<Book> getBooks() {
        return repository.getBooks();
    }

    @Funq
    public void createBook(Book book) {
        repository.addBook(book);
    }

}