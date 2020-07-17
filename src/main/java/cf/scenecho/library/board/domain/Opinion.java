package cf.scenecho.library.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Opinion implements Article {
    @Id
    @GeneratedValue
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
