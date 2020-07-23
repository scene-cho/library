package cf.scenecho.library.book;

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

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService mockBookService;

    static final List<String> getPoints;
    static final List<String> postPoints;

    static {
        getPoints = new ArrayList<>();
        getPoints.add("/books");
        getPoints.add("/books/form");
//        getPoints.add("/books/1");

        postPoints = new ArrayList<>();
        postPoints.add("/books");
    }

    @Test
    void Should_ok_When_getRequestAtEndPoints() throws Exception {
        for (String url : getPoints) mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    void Should_redirect_When_postRequestAtEndPoints() throws Exception {
        for (String url : postPoints) mockMvc.perform(post(url)).andExpect(status().is3xxRedirection());
    }
}