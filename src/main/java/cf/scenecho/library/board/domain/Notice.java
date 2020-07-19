package cf.scenecho.library.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "notice_sequence_generator", sequenceName = "notice_sequence", allocationSize = 1)
public class Notice implements Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_sequence_generator")
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime date;

    public Notice(Article article) {
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
        this.date = LocalDateTime.now();
    }
}
