package cf.scenecho.library.book.borrowing;

import cf.scenecho.library.account.Account;
import cf.scenecho.library.book.Book;
import cf.scenecho.library.book.BookRepository;
import cf.scenecho.library.book.BookService;
import cf.scenecho.library.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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
        Borrowing borrowing = Borrowing.builder()
                .book(bookService.getBook(bookId))
                .borrower(SessionUtil.getAccount(session))
                .startDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy. MM. dd")))
                .build();
        borrowingRepository.save(borrowing);
    }
}
