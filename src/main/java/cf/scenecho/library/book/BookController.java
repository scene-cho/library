package cf.scenecho.library.book;

import cf.scenecho.library.borrowing.BorrowingService;
import cf.scenecho.library.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/form")
    public String registerBookForm(HttpSession session) {
        if (SessionUtil.hasNoAttribute(session)) return "redirect:/accounts/sign-in";
        return "pages/book/form";
    }

    @PostMapping("")
    public String registerBook(Book book, HttpSession session) {
        if (SessionUtil.hasNoAttribute(session)) return "redirect:/accounts/sign-in";
        bookService.registerBook(book);
        return "redirect:/books";
    }

    @GetMapping("")
    public String bookList(Model model) {
        model.addAttribute("books", bookService.bookList());
        return "pages/book/main";
    }

    @GetMapping("/{id}")
    public String bookDetail(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBook(id));
        return "pages/book/detail";
    }
}
