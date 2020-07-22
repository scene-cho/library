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

    public Account signUp(Account account) {
        logger.debug("#signUp: account: {}", account);
        return accountRepository.save(validateAccountId(account));
    }

    private Account validateAccountId(Account account) {
        accountRepository.findById(account.getUserId()).ifPresent(m -> {
            throw new IllegalStateException("Duplicated Id.");
        });
        return account;
    }

    public void signIn(Account attemptingAccount, HttpSession session) {
        logger.debug("#signIn: attemptingAccount: {}, session: {}", attemptingAccount, session);
        session.setAttribute("account", validateAccount(attemptingAccount));
    }

    private Account validateAccount(Account attemptingAccount) {
        Account repoAccount = accountRepository.findById(attemptingAccount.getUserId())
                .orElseThrow(() -> new IllegalStateException("Non-existing Id."));
        return validatePassword(repoAccount, attemptingAccount);
    }

    private Account validatePassword(Account repoAccount, Account attemptingAccount) {
        if (!repoAccount.getPassword().equals(attemptingAccount.getPassword()))
            throw new IllegalStateException("Invalid Password.");
        return repoAccount;
    }

    public void signOut(HttpSession session) {
        logger.debug("#signOut: session: {}", session);
        session.removeAttribute("account");
    }

    public List<Account> accountList() {
        logger.debug("#accountList");
        return accountRepository.findAll();
    }
}
