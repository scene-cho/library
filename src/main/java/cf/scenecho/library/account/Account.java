package cf.scenecho.library.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Account {
    @Id
    private String userId;
    private String password;
    private String email;
    private Boolean adminFlag;
}
