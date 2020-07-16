package cf.scenecho.library.board.core;

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
@RequestMapping("/board")
public class BoardController {
    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{category}/form")
    public String form(@PathVariable String category, Model model) {
        //fixme aop(category-path validation)
        if (category == null) return "404";

        model.addAttribute("category", category);
        return "pages/board/form";
    }

    @GetMapping("/{category}")
    public String getArticles(@PathVariable String category, Model model) {
        if (category == null) return "404";

        List<?> articles = boardService.getArticles(category);
        model.addAttribute("category", category);
        model.addAttribute("articles", articles);
        return "pages/board/main";
    }

    @PostMapping("/{category}")
    public String postArticle(@PathVariable String category, Article article) {
        if (category == null) return "404";

        boardService.postArticle(article, category);
        return "redirect:/board/" + category;
    }

    @GetMapping("/{category}/{id}")
    public String getArticle(@PathVariable String category, @PathVariable Long id, Model model) {
        if (category == null) return "404";

        Optional<?> optionalArticle = boardService.getArticle(id, category);
        if (!optionalArticle.isPresent()) return "404";

        model.addAttribute("article", optionalArticle.get());
        return "pages/board/detail";
    }
}
