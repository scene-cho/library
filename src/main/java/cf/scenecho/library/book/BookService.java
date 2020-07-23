package cf.scenecho.library.book;

import cf.scenecho.library.util.ExceptionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> bookList() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalStateException(ExceptionMessage.NON_EXISTING_ID.toString()));
    }

    public void registerBook(Book book) {
        bookRepository.save(book);
    }
}
