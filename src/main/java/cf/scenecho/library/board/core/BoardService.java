package cf.scenecho.library.board.core;

import cf.scenecho.library.board.notice.Notice;
import cf.scenecho.library.board.notice.NoticeRepository;
import cf.scenecho.library.board.opinion.Opinion;
import cf.scenecho.library.board.opinion.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    // fixme Article Table with DTypeCol or Notice/Opinion Tables
    NoticeRepository noticeRepository;
    OpinionRepository opinionRepository;

    @Autowired
    public BoardService(NoticeRepository noticeRepository, OpinionRepository opinionRepository) {
        this.noticeRepository = noticeRepository;
        this.opinionRepository = opinionRepository;
    }

    public List<?> getArticles(String category) {
        if (category.equals("notices")) return noticeRepository.findAll();
        return opinionRepository.findAll();
    }

    public void postArticle(Article article, String category) {
        article.setDate(LocalDateTime.now());
        // fixme private builder
        if (category.equals("notices")) {
            Notice notice = Notice.builder()
                    .title(article.getTitle())
                    .writer(article.getWriter())
                    .date(article.getDate())
                    .content(article.getContent())
                    .build();
            noticeRepository.save(notice);
        } else {
            Opinion opinion = Opinion.builder()
                    .title(article.getTitle())
                    .writer(article.getWriter())
                    .date(article.getDate())
                    .content(article.getContent())
                    .build();
            opinionRepository.save(opinion);
        }
    }

    public Optional<?> getArticle(Long id, String category) {
        if (category.equals("notices")) return noticeRepository.findById(id);
        return opinionRepository.findById(id);
    }
}
