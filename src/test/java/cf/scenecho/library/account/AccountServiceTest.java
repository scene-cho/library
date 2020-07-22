package cf.scenecho.library.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    Account account;
    HttpSession session;

    @BeforeEach
    void setAccountAndSession() {
        account = Account.builder().userId("me").password("pass").email("a@b.c").adminFlag(true).build();
        session = new MockHttpSession();
    }

    @AfterEach
    void clearRepositoryAndSession() {
        accountRepository.deleteAll();
        session.invalidate();
    }

    @Test
    void Should_When_signUp() {
        accountService.signUp(account);
        assertThat(accountRepository.findById(account.getUserId()).orElse(null)).isEqualTo(account);
        assertThat(accountRepository.findById("other").orElse(null)).isNotEqualTo(account);

        Account newAccount = Account.builder().userId("me").password("password").email("x@y.z").adminFlag(false).build();
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> accountService.signUp(newAccount));
        assertThat(e.getMessage()).isEqualTo("Duplicated Id.");
    }

    @Test
    void signIn() {
        accountService.signUp(account);

        Account attemptingAccount = account;
        accountService.signIn(attemptingAccount, session);
        assertThat(session.getAttribute("account")).isEqualTo(account);
        session.removeAttribute("account");

        Account invalidPasswordAttemptingAccount = Account.builder().userId(account.getUserId()).password("p").build();
        IllegalStateException invalidPasswordException = assertThrows(IllegalStateException.class, () -> accountService.signIn(invalidPasswordAttemptingAccount, session));
        assertThat(invalidPasswordException.getMessage()).isEqualTo("Invalid Password.");
        session.removeAttribute("account");

        Account nonExistingAttemptingAccount = Account.builder().userId("other").password("p").build();
        IllegalStateException nonExistingIdException = assertThrows(IllegalStateException.class, () -> accountService.signIn(nonExistingAttemptingAccount, session));
        assertThat(nonExistingIdException.getMessage()).isEqualTo("Non-existing Id.");
        session.removeAttribute("account");
    }

// todo
//    public List<Account> accountList() {
//        return accountRepository.findAll();
//    }
}