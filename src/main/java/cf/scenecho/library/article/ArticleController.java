package cf.scenecho.library.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/form")
    public String form() {
        logger.debug("#form");
        return "pages/board/form";
    }

    @PostMapping("")
    public String postArticle(Article article) {
        logger.debug("#postArticle: article: {}", article);
        articleService.postArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("")
    public String articleList(Model model) {
        logger.debug("#articleList: model: {}", model);
        List<Article> articles = articleService.articleList();
        model.addAttribute("articles", articles);
        return "pages/board/main";
    }

    @GetMapping("/{id}")
    public String readArticle(@PathVariable Long id, Model model) {
        logger.debug("#readArticle: id: {}, model: {}", id, model);
        Article article = articleService.readArticle(id);
        model.addAttribute("article", article);
        return "pages/board/detail";
    }
}
