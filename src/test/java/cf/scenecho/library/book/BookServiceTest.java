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

    Book book;

    @BeforeEach
    void newBook() {
        book = new Book();
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
    void Should_same_When_get() {
        bookService.registerBook(book);
        Book repoBook = bookService.getBook(book.getId());
        assertSame(book, repoBook);
    }

    @Test
    void Should_throwException_When_findUnregisteredBook() {
        bookService.registerBook(book);
        Book repoBook = bookService.getBook(1L);
        assertNotNull(repoBook);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> bookService.getBook(2L));
        assertEquals(ExceptionMessage.NON_EXISTING_ID.toString(), e.getMessage());
    }

}
