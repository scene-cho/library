package cf.scenecho.library.article;

import cf.scenecho.library.account.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_article_writer"))
    private Account writer;
    private String content;
    private String localDateTime;
    private Boolean noticeFlag;
}