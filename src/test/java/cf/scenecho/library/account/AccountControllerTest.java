package cf.scenecho.library.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @MockBean
    AccountService mockAccountService;

    @Autowired
    MockMvc mockMvc;

    static final List<String> getPoints;
    static final List<String> postPoints;

    static {
        getPoints = new ArrayList<>();
        getPoints.add("/accounts"); // get list of Accounts.
        getPoints.add("/accounts/sign-up"); // form
        getPoints.add("/accounts/sign-in"); // form

        postPoints = new ArrayList<>();
        postPoints.add("/accounts"); // post an account.
        postPoints.add("/accounts/sign-in"); // session
        postPoints.add("/accounts/sign-out");  // session
    }

    @Test
    void Should_ok_When_getRequestAtEndPoints() throws Exception {
        for (String point : getPoints) mockMvc.perform(get(point)).andExpect(status().isOk());
    }

    @Test
    void Should_redirect_When_postRequestAtEndPoints() throws Exception {
        for (String point : postPoints) mockMvc.perform(post(point)).andExpect(status().is3xxRedirection());
    }

}