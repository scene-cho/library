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
    private final NoticeRepository noticeRepository;
    private final OpinionRepository opinionRepository;
    private final Map<String, JpaRepository<? extends Article, Long>> map;

    @Autowired
    public ArticleRepositoryMapping(NoticeRepository noticeRepository, OpinionRepository opinionRepository) {
        this.noticeRepository = noticeRepository;
        this.opinionRepository = opinionRepository;
        this.map = new HashMap<>();
        initMapping();
    }

    private void initMapping() {
        map.put("notices", noticeRepository);
        map.put("opinions", opinionRepository);
    }

    public JpaRepository<? extends Article, Long> getRepository(String category) {
        return map.get(category);
    }

}
