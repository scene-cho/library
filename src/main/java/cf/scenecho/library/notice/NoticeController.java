package cf.scenecho.library.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notices")
public class NoticeController {
    NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/form")
    public String noticeForm() {
        return "pages/notice-form";
    }

    @GetMapping("")
    public String getNotices(Model model) {
        List<Notice> notices = noticeService.getNotices();
        model.addAttribute("notices", notices);
        return "pages/notice";
    }

    @PostMapping("")
    public String postNotice(Notice notice) {
        noticeService.postNotice(notice);
        return "redirect:/notices";
    }

    @GetMapping("/{id}")
    public String getNotice(@PathVariable Long id, Model model) {
        Optional<Notice> notice = noticeService.getNotice(id);
        if (!notice.isPresent()) return "404";
        model.addAttribute("notice", notice.get());
        return "pages/notice-detail";
    }
}
