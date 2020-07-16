package cf.scenecho.library.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<Notice> getNotices() {
        return noticeRepository.findAll();
    }

    public void postNotice(Notice notice) {
        notice.setDate(LocalDateTime.now());
        noticeRepository.save(notice);
    }

    public Optional<Notice> getNotice(Long id) {
        return noticeRepository.findById(id);
    }
}
