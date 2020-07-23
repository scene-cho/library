package cf.scenecho.library.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertSame;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    Book inputBook;

    @BeforeEach
    void BuildBook() {
        inputBook = Book.builder().title("title").author("author").build();
    }


    @Test
    void Should_same_When_save() {
        Book repoBook = bookRepository.save(inputBook);
        assertSame(repoBook, inputBook);
    }

    @Test
    void Should_same_When_find() {
        Book repoBook = bookRepository.save(inputBook);
        Book foundBook = bookRepository.findById(repoBook.getId()).orElse(null);
        assertSame(repoBook, foundBook);
    }

    @Test
    void Should_update_When() {
        Book repoBook = bookRepository.save(inputBook);
        Book foundBook = bookRepository.findById(repoBook.getId()).orElse(null);
        assert foundBook != null;
        foundBook.setAvailable(false);
        assertSame(inputBook, foundBook);
    }
}