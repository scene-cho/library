package cf.scenecho.library.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    Account inputAccount;
    HttpSession session;

    final String SESSION_ATTRIBUTE_NAME_FOR_ACCOUNT = "account";
    final String EXCEPTION_MESSAGE_FOR_DUPLICATED_ID = "Duplicated Id.";
    final String EXCEPTION_MESSAGE_FOR_INVALID_PASSWORD = "Invalid Password.";
    final String EXCEPTION_MESSAGE_FOR_NON_EXISTING_ID = "Non-existing Id.";

    @BeforeEach
    void setAccountAndSession() {
        inputAccount = Account.builder().userId("me").password("pass").email("a@b.c").adminFlag(true).build();
        session = new MockHttpSession();
    }

    @AfterEach
    void clearRepositoryAndSession() {
        accountRepository.deleteAll();
        session.invalidate();
    }

    // Tests for signUp()

    @Test
    void Should_notNull_When_succeedSignUp() {
        accountService.signUp(inputAccount);
        assertNotNull(accountRepository.findById(inputAccount.getUserId()).orElse(null));
    }

    @Test
    void Should_throwException_When_failSignUp() {
        accountService.signUp(inputAccount);
        Account anotherAccountWithSameId = Account.builder().userId(inputAccount.getUserId()).build();
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> accountService.signUp(anotherAccountWithSameId));
        assertEquals(EXCEPTION_MESSAGE_FOR_DUPLICATED_ID, e.getMessage());
    }

    // Tests for signIn()

    @Test
    void Should_sessionHasNoAttribute_When_beforeSignIn() {
        assertNull(session.getAttribute(SESSION_ATTRIBUTE_NAME_FOR_ACCOUNT));
    }

    @Test
    void Should_sessionHasAttribute_When_succeedSignIn() {
        Account account = accountService.signUp(inputAccount);
        Account validApproach = Account.builder().userId(inputAccount.getUserId()).password(inputAccount.getPassword()).build();
        accountService.signIn(validApproach, session);
        Account accountFromSession = (Account) session.getAttribute(SESSION_ATTRIBUTE_NAME_FOR_ACCOUNT);
        assertEquals(account, accountFromSession);
    }

    @Test
    void Should_throwException_When_failSignInForInvalidPassword() {
        accountService.signUp(inputAccount);
        Account invalidPasswordApproach = Account.builder().userId(inputAccount.getUserId()).password("invalid").build();
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> accountService.signIn(invalidPasswordApproach, session));
        assertEquals(EXCEPTION_MESSAGE_FOR_INVALID_PASSWORD, e.getMessage());
        assertNull(session.getAttribute(SESSION_ATTRIBUTE_NAME_FOR_ACCOUNT));
    }

    @Test
    void Should_throwException_When_failSignInForNonExistingId() {
        accountService.signUp(inputAccount);
        Account nonExistingIdApproach = Account.builder().userId("nonExistingId").password("password").build();
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> accountService.signIn(nonExistingIdApproach, session));
        assertEquals(EXCEPTION_MESSAGE_FOR_NON_EXISTING_ID, e.getMessage());
        assertNull(session.getAttribute(SESSION_ATTRIBUTE_NAME_FOR_ACCOUNT));
    }

    // Test for signOut()

    @Test
    void Should_sessionHasNoAttribute_When_signOut() {
        accountService.signUp(inputAccount);
        accountService.signIn(Account.builder().userId(inputAccount.getUserId()).password(inputAccount.getPassword()).build(), session);
        assertNotNull(session.getAttribute(SESSION_ATTRIBUTE_NAME_FOR_ACCOUNT));
        accountService.signOut(session);
        assertNull(session.getAttribute(SESSION_ATTRIBUTE_NAME_FOR_ACCOUNT));
    }

// todo
//    public List<Account> accountList() {
//        return accountRepository.findAll();
//    }
}