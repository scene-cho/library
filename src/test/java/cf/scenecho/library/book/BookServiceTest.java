package cf.scenecho.library.book;

import cf.scenecho.library.util.ExceptionMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;

    Book book;

    @BeforeEach
    void newBook() {
        book = Book.builder().title("title").author("author").build();
    }

    @AfterEach
    void clearRepository() {
    }

    @Test
    void Should_empty_When_beforeResister() {
        List<Book> books = bookService.bookList();
        assertThat(books).isEmpty();
    }

    @Test
    void Should_notEmpty_When_afterResister() {
        bookService.registerBook(book);
        List<Book> books = bookService.bookList();
        assertThat(books).isNotEmpty();
    }

    @Test
    void Should_hasId_When_register() {
        assertNull(book.getId());
        bookService.registerBook(book);
        assertNotNull(book.getId());
    }

    @Test
    void Should_update_When_toggle() {
        bookService.registerBook(book);
        Book repoBook = bookService.getBook(book.getId());
        bookService.toggleAvailability(repoBook);
        assertFalse(repoBook.getAvailable());
        Book repoBook2 = bookService.getBook(book.getId());
        assertFalse(repoBook2.getAvailable());
    }

    @Test
    void Should_throwException_When_findUnregisteredBook() {
        bookService.registerBook(book);
        Book repoBook = bookService.getBook(book.getId());
        assertNotNull(repoBook);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> bookService.getBook(book.getId() + 1));
        assertEquals(ExceptionMessage.NON_EXISTING_ID.toString(), e.getMessage());
    }

}
