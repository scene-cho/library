package cf.scenecho.library.article;

import cf.scenecho.library.account.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final String SESSION_ATTRIBUTE_NAME_FOR_ACCOUNT = "account";

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/form")
    public String form(HttpSession session) {
        logger.debug("#form: session: {}", session);
        if (hasNoAttribute(session)) return "redirect:/accounts/sign-in";
        return "pages/article/form";
    }

    private boolean hasNoAttribute(HttpSession session) {
        return session.getAttribute(SESSION_ATTRIBUTE_NAME_FOR_ACCOUNT) == null;
    }

    @PostMapping("")
    public String postArticle(Article article, HttpSession session) {
        logger.debug("#postArticle: article: {}, session: {}", article, session);
        if (hasNoAttribute(session)) return "redirect:/accounts/sign-in";
        articleService.postArticle(article, (Account) session.getAttribute(SESSION_ATTRIBUTE_NAME_FOR_ACCOUNT));
        return "redirect:/articles";
    }

    @GetMapping("")
    public String articleList(Model model) {
        logger.debug("#articleList: model: {}", model);
        List<Article> articles = articleService.articleList();
        model.addAttribute("articles", articles);
        return "pages/article/main";
    }

    @GetMapping("/{id}")
    public String readArticle(@PathVariable Long id, Model model) {
        logger.debug("#readArticle: id: {}, model: {}", id, model);
        Article article = articleService.readArticle(id);
        model.addAttribute("article", article);
        return "pages/article/detail";
    }
}
