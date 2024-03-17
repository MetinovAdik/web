package com.techdragons.web.entity;


import com.techdragons.web.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {
    private final RoleRepository roleRepository;

    @Autowired
    public DataSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        if (roleRepository.findByName(RoleName.ROLE_STUDENT).isEmpty()) {
            roleRepository.save(new Role(null, RoleName.ROLE_STUDENT));
        }
        if (roleRepository.findByName(RoleName.ROLE_TEACHER).isEmpty()) {
            roleRepository.save(new Role(null, RoleName.ROLE_TEACHER));
        }
    }
}