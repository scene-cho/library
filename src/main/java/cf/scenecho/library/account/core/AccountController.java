package cf.scenecho.library.account.core;

import cf.scenecho.library.account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // aop
    @GetMapping("{authority}/form")
    public String form(@PathVariable String authority, Model model) {
        model.addAttribute("authority", authority);
        return "pages/account/form";
    }

    @GetMapping("{authority}")
    public String getAccounts(@PathVariable String authority, Model model) {
        model.addAttribute("authority", authority);
        model.addAttribute("accounts", accountService.getAccounts(authority));
        return "pages/account/main";
    }

    @PostMapping("{authority}")
    public String signUp(@PathVariable String authority, Account account) {
        accountService.signUp(authority, account);
        return "redirect:/";
    }
}
