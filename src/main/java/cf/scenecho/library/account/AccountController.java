package cf.scenecho.library.account;

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

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/sign-up")
    public String signUpForm() {
        return "pages/account/sign-up-form";
    }

    @PostMapping("")
    public String signUp(Account account, HttpSession session) {
        accountService.signUp(account);
        accountService.signIn(account, session);
        return "redirect:/";
    }

    @GetMapping("/sign-in")
    public String signInForm() {
        return "pages/account/sign-in-form";
    }

    @PostMapping("/sign-in")
    public String signIn(Account account, HttpSession session) {
        accountService.signIn(account, session);
        return "redirect:/";
    }

    @PostMapping("/sign-out")
    public String signOut(HttpSession session) {
        accountService.signOut(session);
        return "redirect:/";
    }

    @GetMapping("")
    public String accountList(Model model) {
        model.addAttribute("accounts", accountService.accountList());
        return "pages/account/main";
    }
}
