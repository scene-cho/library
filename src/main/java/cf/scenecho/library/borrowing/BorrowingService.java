package cf.scenecho.library.borrowing;

import cf.scenecho.library.book.Book;
import cf.scenecho.library.book.BookService;
import cf.scenecho.library.util.ExceptionMessage;
import cf.scenecho.library.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        book.setAvailable(false);
        Borrowing borrowing = Borrowing.builder()
                .book(book)
                .borrower(SessionUtil.getAccount(session))
                .startDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy. MM. dd")))
                .build();
        borrowingRepository.save(borrowing);
    }

    public void returnBook(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id).orElseThrow(() -> new IllegalStateException(ExceptionMessage.NON_EXISTING_ID.toString()));
        borrowing.getBook().setAvailable(true);
        borrowingRepository.delete(borrowing);
    }

    public List<Borrowing> myBorrowings(HttpSession session) {
        return borrowingRepository.findByBorrower(SessionUtil.getAccount(session));
    }

    public Borrowing getBorrowing(Long id) {
        return borrowingRepository.findById(id).orElseThrow(() -> new IllegalStateException(ExceptionMessage.NON_EXISTING_ID.toString()));
    }
}
