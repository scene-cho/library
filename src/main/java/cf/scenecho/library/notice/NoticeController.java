package cf.scenecho.library.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class NoticeController {
    NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notices")
    public String notice(Model model) {
//        List<Notice> notices = noticeService.getNotices();
        List<Notice> notices = new ArrayList<>();
        notices.add(Notice.builder()
                .title("t").writer("w").date(LocalDateTime.now()).build());
        notices.add(Notice.builder()
                .title("t2").writer("w2").date(LocalDateTime.now()).build());
        model.addAttribute("notices", notices);
        return "pages/notice";
    }
}
