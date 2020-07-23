package cf.scenecho.library.borrowing;

import cf.scenecho.library.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByBorrower(Account borrower);
}
