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
        if (category.equals("notices"))
            ((NoticeRepository) repositoryMapping.getRepository(category)).save(new Notice(article));
        else
            ((OpinionRepository) repositoryMapping.getRepository(category)).save(new Opinion(article));
    }

    public Article getArticle(Long id, String category) {
        return repositoryMapping.getRepository(category).findById(id).orElse(null);
    }
}
