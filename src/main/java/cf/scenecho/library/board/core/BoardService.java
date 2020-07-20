package cf.scenecho.library.board.core;

import cf.scenecho.library.board.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    ArticleRepositoryMapping articleRepositoryMapping;

    @Autowired
    public BoardService(ArticleRepositoryMapping articleRepositoryMapping) {
        this.articleRepositoryMapping = articleRepositoryMapping;
    }

    public List<? extends Article> getArticles(String category) {
        return articleRepositoryMapping.getRepository(category).findAll();
    }

    public void postArticle(String category, Article article) {
        switch (category) {
            case "notices":
                ((NoticeRepository) articleRepositoryMapping.getRepository(category)).save(new Notice(article));
                return;
            case "opinions":
                ((OpinionRepository) articleRepositoryMapping.getRepository(category)).save(new Opinion(article));
                return;
            default:
        }
    }

    public Article getArticle(Long id, String category) {
        return articleRepositoryMapping.getRepository(category).findById(id).orElse(null);
    }
}
