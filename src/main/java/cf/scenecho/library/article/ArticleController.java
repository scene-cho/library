package cf.scenecho.library.article;

import cf.scenecho.library.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/form")
    public String form(HttpSession session) {
        if (SessionUtil.hasNoAttribute(session)) return "redirect:/accounts/sign-in";
        return "pages/article/form";
    }

    @PostMapping("")
    public String postArticle(Article article, HttpSession session) {
        if (SessionUtil.hasNoAttribute(session)) return "redirect:/accounts/sign-in";
        articleService.postArticle(article, SessionUtil.getAccount(session));
        return "redirect:/articles";
    }

    @GetMapping("")
    public String articleList(Model model) {
        List<Article> articles = articleService.articleList();
        model.addAttribute("articles", articles);
        return "pages/article/main";
    }

    @GetMapping("/{id}")
    public String readArticle(@PathVariable Long id, Model model) {
        Article article = articleService.readArticle(id);
        model.addAttribute("article", article);
        return "pages/article/detail";
    }
}