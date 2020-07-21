package cf.scenecho.library.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void signUp(Account account) {
        logger.debug("#signUp: account: {}", account);
        accountRepository.save(account);
    }

    public boolean signIn(Account attemptingAccount, HttpSession session) {
        logger.debug("#signIn: attemptingAccount: {}, session: {}", attemptingAccount, session);
        Account account = accountRepository.findById(attemptingAccount.getUserId()).orElse(null);
        logger.debug("#signIn: account: {}", account);
        if (account == null) return false;
        if (!account.getPassword().equals(attemptingAccount.getPassword())) return false;
        session.setAttribute("account", account);
        return true;
    }

    public void signOut(HttpSession session) {
        logger.debug("#signOut: session: {}", session);
        session.removeAttribute("account");
    }

    // fixme
    public List<Account> accountList() {
        return accountRepository.findAll();
    }
}
