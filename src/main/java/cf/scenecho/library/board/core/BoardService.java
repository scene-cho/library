package cf.scenecho.library.board.core;

import cf.scenecho.library.board.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    RepositoryMapping repositoryMapping;

    @Autowired
    public BoardService(RepositoryMapping repositoryMapping) {
        this.repositoryMapping = repositoryMapping;
    }

    public List<? extends Article> getArticles(String category) {
        return repositoryMapping.getRepository(category).findAll();
    }

    public void postArticle(String category, Article article) {
        switch (category) {
            case "notices":
                ((NoticeRepository) repositoryMapping.getRepository(category)).save(new Notice(article));
                return;
            case "opinions":
                ((OpinionRepository) repositoryMapping.getRepository(category)).save(new Opinion(article));
                return;
            default:
        }
    }

    public Article getArticle(Long id, String category) {
        return repositoryMapping.getRepository(category).findById(id).orElse(null);
    }
}
