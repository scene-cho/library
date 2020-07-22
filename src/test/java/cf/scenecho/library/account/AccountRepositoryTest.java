package cf.scenecho.library.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    Account inputAccount;

    @BeforeEach
    void buildAccount() {
        inputAccount = Account.builder().userId("me").password("pass").email("a@b.c").adminFlag(false).build();
    }

    @AfterEach
    void clearRepository() {
        accountRepository.deleteAll();
    }

    @Test
    void Should_notSameButEquals_When_save() {
        Account repoAccount = accountRepository.save(inputAccount);
        assertNotSame(inputAccount, repoAccount);
        assertEquals(inputAccount, repoAccount);
    }

    @Test
    void Should_same_When_find() {
        Account repoAccount = accountRepository.save(inputAccount);
        Optional<Account> foundAccount = accountRepository.findById(repoAccount.getUserId());
        assertSame(repoAccount, foundAccount.orElse(null));
    }

    @Test
    void Should_null_When_find() {
        accountRepository.save(inputAccount);
        Optional<Account> foundAccount = accountRepository.findById("nonExistingId");
        assertNull(foundAccount.orElse(null));
    }

    @Test
    void Should_containsEqualElement_When_findAll() {
        accountRepository.save(inputAccount);
        List<Account> accountList = accountRepository.findAll();
        assertThat(accountList).contains(inputAccount);
    }

    @Test
    void Should_doesNotContainNotEqualElement_When_findAll() {
        accountRepository.save(inputAccount);
        Account notEqualAccountWithSameId = Account.builder().userId(inputAccount.getUserId()).build();
        assertThat(accountRepository.findAll()).doesNotContain(notEqualAccountWithSameId);
    }

    @Test
    void Should_update_When_saveSameId() {
        Account repoAccount = accountRepository.save(inputAccount);
        Account repoUpdatedAccount = accountRepository.save(Account.builder().userId(repoAccount.getUserId()).password("password").build());
        assertSame(repoAccount, repoUpdatedAccount);
    }

    @Test
    void Should_update_When_reSetField() {
        Account repoAccount = accountRepository.save(inputAccount);
        repoAccount.setPassword("newPassword");
        assertSame(repoAccount, accountRepository.findById(inputAccount.getUserId()).orElse(null));
    }
}