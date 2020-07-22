package cf.scenecho.library.article;

import cf.scenecho.library.account.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void postArticle(Article article, Account writer) {
        logger.debug("#postArticle: article: {}, writer: {}", article, writer);
        article.setWriter(writer);
        article.setLocalDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy. MM. dd, hh : mm")));
        articleRepository.save(article);
    }

    public List<Article> articleList() {
        logger.debug("#articleList");
        return articleRepository.findAll();
    }


    public Article readArticle(Long id) {
        logger.debug("#readArticle: id: {}", id);
        return articleRepository.findById(id).orElse(null);
    }
}
