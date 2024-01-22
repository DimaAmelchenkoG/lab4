
package letsCode.services;

import letsCode.models.User;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;

@NoRepositoryBean
public interface MyUserService extends UserDetailsService {

    boolean findByLogin(String userName);
    User getUserByLogin(String userName);
    void addUser(User user);
}
