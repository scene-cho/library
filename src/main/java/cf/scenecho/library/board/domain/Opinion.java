package cf.scenecho.library.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "opinion_sequence_generator", sequenceName = "opinion_sequence", allocationSize = 1)
public class Opinion implements Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opinion_sequence_generator")
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime date;

    public Opinion(Article article) {
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
        this.date = LocalDateTime.now();
    }
}
