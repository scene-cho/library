package cf.scenecho.library.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/form")
    public String registerBookForm() {
        return "pages/book/form";
    }

    @PostMapping("")
    public String registerBook() {

        return "redirect:/books";
    }

    @GetMapping("")
    public String bookList() {

        return "pages/book/main";
    }

    @GetMapping("/{id}")
    public String bookDetail(@PathVariable Long id) {

        return "pages/book/detail";
    }
}
