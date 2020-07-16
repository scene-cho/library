package cf.scenecho.library.board.notice;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String writer;
    private LocalDateTime date;
    private String content;
}
