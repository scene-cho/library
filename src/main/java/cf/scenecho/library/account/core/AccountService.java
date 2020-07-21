package cf.scenecho.library.account.core;

import cf.scenecho.library.account.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

    public boolean signIn(String authority, Account account, HttpSession session) {
        JpaRepository<? extends Account, String> repository = accountRepositoryMapping.getRepository(authority);
        Optional<? extends Account> optionalAccount = repository.findById(account.getUserId());
        if (!optionalAccount.isPresent()) return false;
        if (!optionalAccount.get().getPassword().equals(account.getPassword())) return false;
        session.setAttribute("account", optionalAccount.get());
        return true;
    }
}
