package cf.scenecho.library.account;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Account {
    @Id
    private String userId;
    private String password;
    private String email;
    private Boolean adminFlag;
}
