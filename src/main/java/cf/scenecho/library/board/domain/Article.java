package cf.scenecho.library.board.domain;

import cf.scenecho.library.account.domain.Account;

public interface Article {
    Long getId();

    String getDate();

    String getTitle();

    Account getWriter();

    String getContent();
}