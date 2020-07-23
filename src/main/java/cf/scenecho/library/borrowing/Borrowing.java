package cf.scenecho.library.borrowing;

import cf.scenecho.library.account.Account;
import cf.scenecho.library.book.Book;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Borrowing {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn
    private Account borrower;
    @ManyToOne
    @JoinColumn
    private Book book;
    private String startDate;
}
