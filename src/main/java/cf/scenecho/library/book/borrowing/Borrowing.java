package cf.scenecho.library.book.borrowing;

import cf.scenecho.library.account.Account;
import cf.scenecho.library.book.Book;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
public class Borrowing {
    @Id
    @GeneratedValue
    Long borrowingId;
    @ManyToOne
    @JoinColumn
    Account borrower;
    @ManyToOne
    @JoinColumn
    Book book;
    String startDate;
}
