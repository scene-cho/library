package cf.scenecho.library.article;

import cf.scenecho.library.account.Account;
import cf.scenecho.library.account.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    AccountRepository accountRepository;

    Account writer;
    Article inputArticle;

    @BeforeEach
    void saveAccountAndBuildArticle() {
        writer = accountRepository.save(Account.builder().userId("writer").build());
        inputArticle = Article.builder().title("title").writer(writer).content("content").localDateTime(LocalDateTime.now()).noticeFlag(false).build();
    }

    @AfterEach
    void clearRepository() {
        articleRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void Should_same_When_save() {
        Article repoArticle = articleRepository.save(inputArticle);
        assertSame(inputArticle, repoArticle);
    }

    @Test
    void Should_setId_When_save() {
        assertNull(inputArticle.getId());
        articleRepository.save(inputArticle);
        assertNotNull(inputArticle.getId());
    }

    @Test
    void Should_same_When_find() {
        Article repoArticle = articleRepository.save(inputArticle);
        Optional<Article> foundArticle = articleRepository.findByTitle(repoArticle.getTitle());
        assertSame(repoArticle, foundArticle.orElse(null));
    }

    @Test
    void Should_notEqual_When_compareGeneratedId() {
        Article repoArticle = articleRepository.save(inputArticle);
        Article anotherRepoArticle = articleRepository.save(Article.builder().title(repoArticle.getTitle()).writer(repoArticle.getWriter()).content(repoArticle.getContent()).localDateTime(repoArticle.getLocalDateTime()).noticeFlag(repoArticle.getNoticeFlag()).build());
        assertNotEquals(repoArticle, anotherRepoArticle);
    }

    @Test
    void Should_null_When_find() {
        articleRepository.save(inputArticle);
        Optional<Article> foundArticle = articleRepository.findByTitle("unknown Title");
        assertNull(foundArticle.orElse(null));
    }

    @Test
    void Should_containsEqualElement_When_findAll() {
        articleRepository.save(inputArticle);
        List<Article> articleList = articleRepository.findAll();
        assertThat(articleList).contains(inputArticle);
    }

    @Test
    void Should_increaseSize_When_saveAndFindAll() {
        assertEquals(0, articleRepository.findAll().size());
        articleRepository.save(inputArticle);
        assertEquals(1, articleRepository.findAll().size());
    }

    @Test
    void Should_update_When_saveSameId() {
        Article repoArticle = articleRepository.save(inputArticle);
        Article repoUpdatedArticle = articleRepository.save(Article.builder().id(repoArticle.getId()).content("another Content").build());
        assertSame(repoArticle, repoUpdatedArticle);
    }

    @Test
    void Should_update_When_reSetField() {
        Article repoArticle = articleRepository.save(inputArticle);
        repoArticle.setContent("new Content");
        assertSame(repoArticle, articleRepository.findById(inputArticle.getId()).orElse(null));
    }
}