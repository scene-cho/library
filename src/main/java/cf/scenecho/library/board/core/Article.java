package cf.scenecho.library.board.core;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String writer;
    private LocalDateTime date;
    private String content;
}