package cf.scenecho.library.borrowing;

import cf.scenecho.library.book.Book;
import cf.scenecho.library.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/borrowings")
public class BorrowingController {
    BorrowingService borrowingService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("")
    public String borrowBook(Long bookId, HttpSession session) {
        if (SessionUtil.hasNoAttribute(session)) return "redirect:/accounts/sign-in";
        borrowingService.borrowBook(bookId, session);
        return "redirect:/borrowings";
    }

    @GetMapping("")
    public String myBorrowings(Model model, HttpSession session) {
        if (SessionUtil.hasNoAttribute(session)) return "redirect:/accounts/sign-in";
        model.addAttribute("borrowings", borrowingService.myBorrowings(session));
        return "/pages/borrow/main";
    }

    @GetMapping("/{id}")
    public String borrowingDetail(@PathVariable Long id, HttpSession session, Model model) {
        if (SessionUtil.hasNoAttribute(session)) return "redirect:/accounts/sign-in";
        model.addAttribute("borrowing", borrowingService.getBorrowing(id));
        return "/pages/borrow/detail";
    }

    @DeleteMapping("/{id}")
    public String returnBook(@PathVariable Long id, HttpSession session) {
        if (SessionUtil.hasNoAttribute(session)) return "redirect:/accounts/sign-in";
        borrowingService.returnBook(id);
        return "redirect:/borrowings";
    }
}
