package cf.scenecho.library.board.domain;

import cf.scenecho.library.account.domain.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "notice_sequence_generator", sequenceName = "notice_sequence", allocationSize = 1)
public class Notice implements Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_sequence_generator")
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_notice_writer"))
    private Admin writer;
    private String content;
    private String date;

    public Notice(Article article) {
        this.title = article.getTitle();
        this.writer = (Admin) article.getWriter();
        this.content = article.getContent();
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
}
