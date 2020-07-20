package cf.scenecho.library.account.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Admin implements Account {
    @Id
    String userId;
    String password;
    String email;

    public Admin(Account account) {
        this.userId = account.getUserId();
        this.password = account.getPassword();
        this.email = account.getEmail();
    }
}