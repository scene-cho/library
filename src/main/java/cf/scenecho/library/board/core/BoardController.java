package cf.scenecho.library.board.core;

import cf.scenecho.library.board.domain.Article;
import cf.scenecho.library.board.util.ValidateCategoryPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{category}/form")
    @ValidateCategoryPath
    public String form(@PathVariable String category, Model model) {
        model.addAttribute("category", category);
        return "pages/board/form";
    }

    @GetMapping("/{category}")
    @ValidateCategoryPath
    public String getArticles(@PathVariable String category, Model model) {
        List<? extends Article> articles = boardService.getArticles(category);
        model.addAttribute("category", category);
        model.addAttribute("articles", articles);
        return "pages/board/main";
    }

    @PostMapping("/{category}")
    @ValidateCategoryPath
    public String postArticle(@PathVariable String category, Article article) {
        boardService.postArticle(category, article);
        return "redirect:/board/" + category;
    }

    @GetMapping("/{category}/{id}")
    @ValidateCategoryPath
    public String getArticle(@PathVariable String category, @PathVariable Long id, Model model) {
        Article article = boardService.getArticle(id, category);
        if (article == null) return "404";

        model.addAttribute("article", article);
        return "pages/board/detail";
    }
}
