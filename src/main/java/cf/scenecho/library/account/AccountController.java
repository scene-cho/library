package cf.scenecho.library.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/sign-up")
    public String signUpForm() {
        logger.debug("#signUpForm");
        return "pages/account/sign-up-form";
    }

    @PostMapping("")
    public String signUp(Account account) {
        logger.debug("#signUp: account: {}", account);
        accountService.signUp(account);
        return "redirect:/";
    }

    @GetMapping("/sign-in")
    public String signInForm() {
        logger.debug("#signInForm");
        return "pages/account/sign-in-form";
    }

    @PostMapping("/sign-in")
    public String signIn(Account account, HttpSession session) {
        logger.debug("#signIn: account: {}, session: {}", account, session);
        accountService.signIn(account, session);
        return "redirect:/";
    }

    @PostMapping("/sign-out")
    public String signOut(HttpSession session) {
        logger.debug("#signOut: session: {}", session);
        accountService.signOut(session);
        return "redirect:/";
    }

    //    fixme
    @GetMapping("")
    public String accountList(Model model) {
        model.addAttribute("accounts", accountService.accountList());
        return "pages/account/main";
    }
}
