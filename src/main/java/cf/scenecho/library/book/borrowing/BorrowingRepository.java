package cf.scenecho.library.book.borrowing;

import cf.scenecho.library.account.Account;
import cf.scenecho.library.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Optional<Borrowing> findByBookAndBorrower(Book book, Account account);
}
