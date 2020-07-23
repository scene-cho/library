package cf.scenecho.library.account;

import cf.scenecho.library.util.ExceptionMessage;
import cf.scenecho.library.util.SessionUtil;
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
        return accountRepository.save(validateAccountId(account));
    }

    public void signIn(Account approachingAccount, HttpSession session) {
        SessionUtil.setAccount(session, validateApproach(approachingAccount));
    }

    public void signOut(HttpSession session) {
        SessionUtil.removeAccount(session);
    }

    public List<Account> accountList() {
        return accountRepository.findAll();
    }

    // private

    private Account validateAccountId(Account account) {
        logger.debug("- validating id... account: {}", account);
        accountRepository.findById(account.getUserId()).ifPresent(m -> {
            throw new IllegalStateException(ExceptionMessage.DUPLICATED_ID.toString());
        });
        return account;
    }

    private Account validateApproach(Account approachingAccount) {
        logger.debug("- validating approaching id... account: {}", approachingAccount);
        Account repoAccount = accountRepository.findById(approachingAccount.getUserId())
                .orElseThrow(() -> new IllegalStateException(ExceptionMessage.NON_EXISTING_ID.toString()));
        return validatePassword(repoAccount, approachingAccount);
    }

    private Account validatePassword(Account repoAccount, Account approachingAccount) {
        logger.debug("- validating approaching password... account: {}", approachingAccount);
        if (!repoAccount.getPassword().equals(approachingAccount.getPassword()))
            throw new IllegalStateException(ExceptionMessage.INVALID_PASSWORD.toString());
        return repoAccount;
    }
}