package cf.scenecho.library.board.domain;

import java.time.LocalDateTime;

public interface Article {
    Long getId();

    LocalDateTime getDate();

    String getTitle();

    String getWriter();

    String getContent();
}