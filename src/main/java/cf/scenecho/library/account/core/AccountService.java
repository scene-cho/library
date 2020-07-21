package cf.scenecho.library.account.core;

import cf.scenecho.library.account.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepositoryMapping accountRepositoryMapping;

    @Autowired
    public AccountService(AccountRepositoryMapping accountRepositoryMapping) {
        this.accountRepositoryMapping = accountRepositoryMapping;
    }

    public List<? extends Account> getAccounts(String authority) {
        return accountRepositoryMapping.getRepository(authority).findAll();
    }

    public void signUp(String authority, Account account) {
        switch (authority) {
            case "members":
                ((MemberRepository) accountRepositoryMapping.getRepository(authority)).save(new Member(account));
                return;
            case "admins":
                ((AdminRepository) accountRepositoryMapping.getRepository(authority)).save(new Admin(account));
                return;
            default:
        }
    }

    public boolean signIn(String authority, Account account) {
        Optional<? extends Account> byId = accountRepositoryMapping.getRepository(authority).findById(account.getUserId());
        return byId.map(value -> value.getPassword().equals(account.getPassword())).orElse(false);
    }
}
