package letsCode.services;

import letsCode.models.Role;
import letsCode.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role getUserRole(String roleName){
        return roleRepository.findByName(roleName).get();
    }
}
