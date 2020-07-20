package cf.scenecho.library.board.domain;

import cf.scenecho.library.account.domain.Account;

import java.time.LocalDateTime;

public interface Article {
    Long getId();

    LocalDateTime getDate();

    String getTitle();

    Account getWriter();

    String getContent();
}