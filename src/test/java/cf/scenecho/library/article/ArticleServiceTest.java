package cf.scenecho.library.article;

import cf.scenecho.library.account.Account;
import cf.scenecho.library.account.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        inputArticle = Article.builder().title("title").writer(writer).content("content").localDateTime(LocalDateTime.now()).noticeFlag(false).build();
    }

    @AfterEach
    void clearRepository() {
        articleRepository.deleteAll();
    }

    // Tests for postArticle() and articleList()

    @Test
    void Should_empty_When_beforePost() {
        List<Article> articleList = articleService.articleList();
        assertThat(articleList).isEmpty();
    }

    @Test
    void Should_increaseSize_When_post() {
        assertEquals(0, articleService.articleList().size());
        articleService.postArticle(inputArticle);
        assertEquals(1, articleService.articleList().size());
    }

    @Test
    void Should_notIncreaseSize_When_postSameArticle() {
        articleService.postArticle(inputArticle);
        articleService.postArticle(inputArticle);
        assertEquals(1, articleService.articleList().size());
    }

    // Tests for readArticle()

}
