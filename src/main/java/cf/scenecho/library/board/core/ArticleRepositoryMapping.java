package cf.scenecho.library.board.core;

import cf.scenecho.library.board.domain.Article;
import cf.scenecho.library.board.domain.NoticeRepository;
import cf.scenecho.library.board.domain.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ArticleRepositoryMapping {
    NoticeRepository noticeRepository;
    OpinionRepository opinionRepository;
    private Map<String, JpaRepository<? extends Article, Long>> map;

    @Autowired
    public ArticleRepositoryMapping(NoticeRepository noticeRepository, OpinionRepository opinionRepository) {
        this.noticeRepository = noticeRepository;
        this.opinionRepository = opinionRepository;
        initMapping();
    }

    private void initMapping() {
        this.map = new HashMap<>();
        map.put("notices", noticeRepository);
        map.put("opinions", opinionRepository);
    }

    public JpaRepository<? extends Article, Long> getRepository(String category) {
        return map.get(category);
    }

}
