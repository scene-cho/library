package cf.scenecho.library.article;

import cf.scenecho.library.account.Account;
import cf.scenecho.library.util.SessionUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @MockBean
    ArticleService mockArticleService;

    @Autowired
    MockMvc mockMvc;

    static final List<String> getPoints;
    static final List<String> postPoints;

    static {
        getPoints = new ArrayList<>();
        getPoints.add("/articles"); // get list of articles.
//        getPoints.add("/articles/1"); // read an article.
        getPoints.add("/articles/form"); // form

        postPoints = new ArrayList<>();
        postPoints.add("/articles"); // post an article.
    }

    @Test
    void Should_ok_When_getRequestAtEndPoints() throws Exception {
        for (String point : getPoints)
            mockMvc.perform(get(point).sessionAttr(SessionUtil.ACCOUNT, new Account()))
                    .andExpect(status().isOk());
    }

    @Test
    void Should_redirect_When_postRequestAtEndPoints() throws Exception {
        for (String point : postPoints)
            mockMvc.perform(post(point))
                    .andExpect(status().is3xxRedirection());
    }

}
