package cf.scenecho.library.account.core;

import cf.scenecho.library.account.domain.Account;
import cf.scenecho.library.account.domain.AdminRepository;
import cf.scenecho.library.account.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountRepositoryMapping {
    MemberRepository memberRepository;
    AdminRepository adminRepository;
    private Map<String, JpaRepository<? extends Account, String>> map;

    @Autowired

    public AccountRepositoryMapping(MemberRepository memberRepository, AdminRepository adminRepository) {
        this.memberRepository = memberRepository;
        this.adminRepository = adminRepository;
        initMapping();
    }

    private void initMapping() {
        map = new HashMap<>();
        map.put("members", memberRepository);
        map.put("admins", adminRepository);
    }

    public JpaRepository<? extends Account, String> getRepository(String authority) {
        return map.get(authority);
    }
}
