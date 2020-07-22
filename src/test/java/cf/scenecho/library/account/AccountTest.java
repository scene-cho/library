package cf.scenecho.library.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void Should_notSameButEquals_When_hasSameFieldValue() {
        Account account1 = Account.builder().userId("me").build();
        Account account2 = Account.builder().userId("me").build();
        assertNotSame(account1, account2);
        assertEquals(account1, account2);
    }

    @Test
    void Should_notEquals_When_hasDifferentFieldValue() {
        Account account1 = Account.builder().userId("me").password("pass").build();
        Account account2 = Account.builder().userId("me").password("password").build();
        assertNotEquals(account1, account2);
    }
}