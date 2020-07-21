package cf.scenecho.library.article;

import cf.scenecho.library.account.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Article {
    @Id
    @GeneratedValue
    Long id;
    String title;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_article_writer"))
    Account writer;
    String content;
    LocalDateTime localDateTime;
    Boolean noticeFlag;
}