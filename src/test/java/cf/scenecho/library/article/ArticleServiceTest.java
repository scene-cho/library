package cf.scenecho.library.article;

import cf.scenecho.library.account.Account;
import cf.scenecho.library.account.AccountRepository;
import cf.scenecho.library.util.ExceptionMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    AccountRepository accountRepository;

    Account writer;
    Article inputArticle;

    @BeforeEach
    void saveWriterAndBuildArticle() {
        writer = accountRepository.save(Account.builder().userId("writer").build());
        inputArticle = Article.builder().title("title").content("content").localDateTime("2020. 1. 1").noticeFlag(false).build();
    }

    @AfterEach
    void clearRepository() {
        articleRepository.deleteAll();
    }

    @Test
    void Should_empty_When_beforePost() {
        List<Article> articleList = articleService.articleList();
        assertThat(articleList).isEmpty();
    }

    @Test
    void Should_throwException_When_findBeforePost() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> articleService.readArticle(1L));
        assertEquals(ExceptionMessage.NON_EXISTING_ID.toString(), e.getMessage());
    }

    @Test
    void Should_increaseSize_When_post() {
        assertEquals(0, articleService.articleList().size());
        articleService.postArticle(inputArticle, writer);
        assertEquals(1, articleService.articleList().size());
    }

    @Test
    void Should_notIncreaseSize_When_postSameArticle() {
        articleService.postArticle(inputArticle, writer);
        articleService.postArticle(inputArticle, writer);
        assertEquals(1, articleService.articleList().size());
    }
}
