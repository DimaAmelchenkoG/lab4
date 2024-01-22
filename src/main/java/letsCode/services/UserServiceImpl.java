
package letsCode.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import letsCode.models.Role;
import letsCode.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, MyUserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService){
        this.userRepository = userRepository;
        this.roleService = roleService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("LOAD BY USERNAME");
        letsCode.models.User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return new User(user.getUsername(), user.getPassword(), user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }


    @Transactional
    @Override
    public boolean findByLogin(String userName){
        try {
            userRepository.findByUsername(userName).get();
        }catch (NoSuchElementException ex){
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public letsCode.models.User getUserByLogin(String userName){
        return userRepository.findByUsername(userName).get();
    }

    @Transactional
    @Override
    public void addUser(letsCode.models.User user){
        System.out.println("Hello");
        Role role = new Role();
        role.setName("ROLE_USER");
        user.setRoles(List.of(role));
        System.out.println("BEFORE");
        userRepository.save(user);
        System.out.println("After");
    }
}