package cf.scenecho.library.book.borrowing;

import cf.scenecho.library.book.Book;
import cf.scenecho.library.book.BookService;
import cf.scenecho.library.util.ExceptionMessage;
import cf.scenecho.library.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BookService bookService;

    @Autowired
    public BorrowingService(BorrowingRepository borrowingRepository, BookService bookService) {
        this.borrowingRepository = borrowingRepository;
        this.bookService = bookService;
    }

    public void borrowBook(Long bookId, HttpSession session) {
        Book book = bookService.getBook(bookId);
        if (!book.getAvailable()) throw new IllegalStateException(ExceptionMessage.NOT_AVAILABLE.toString());
        Borrowing borrowing = Borrowing.builder()
                .book(book)
                .borrower(SessionUtil.getAccount(session))
                .startDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy. MM. dd")))
                .build();
        borrowingRepository.save(borrowing);
        book.setAvailable(false);
    }

    public void returnBook(Long bookId, HttpSession session) {
        Book book = bookService.getBook(bookId);
        Borrowing borrowing = borrowingRepository.findByBookAndBorrower(book, SessionUtil.getAccount(session))
                .orElseThrow(() -> new IllegalStateException(ExceptionMessage.NON_EXISTING_ID.toString()));
        borrowingRepository.delete(borrowing);
        book.setAvailable(true);
    }
}
