package cf.scenecho.library.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    Account account;

    @BeforeEach
    void setUp() {
        account = Account.builder().userId("me").password("pass").email("a@b.c").adminFlag(false).build();
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void saveAndFind() {
        Account repoAccount = accountRepository.save(account);
        assertThat(repoAccount).isNotNull();
        assertEquals(account, repoAccount);
        assertEquals(account.toString(), repoAccount.toString());

        Optional<Account> existingAccount = accountRepository.findById(repoAccount.getUserId());
        assertThat(existingAccount).isNotEmpty();
        assertEquals(repoAccount, existingAccount.orElse(null));

        Optional<Account> nonExistingAccount = accountRepository.findById("other");
        assertThat(nonExistingAccount).isEmpty();

        List<Account> accountList = accountRepository.findAll();
        assertThat(accountList).contains(repoAccount);
        assertThat(accountList).doesNotContain(Account.builder().userId("other").build());
        assertEquals(1, accountList.size());
    }

    @Test
    void update() {
        Account repoAccount = accountRepository.save(account);

        Account updatedAccount = Account.builder()
                .userId(repoAccount.getUserId()).password("password")
                .email(repoAccount.getEmail()).adminFlag(true).build();
        Account repoUpdatedAccount = accountRepository.save(updatedAccount);

        assertEquals(repoUpdatedAccount, updatedAccount);
        assertEquals(repoAccount, repoUpdatedAccount);
    }
}