package com.summertraining.iysproject.controller;

import com.summertraining.iysproject.model.Role;
import com.summertraining.iysproject.repo.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Service
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/role")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/role/{id}")
    public Optional<Role> getRole(@PathVariable Long id) {
        return roleRepository.findById(id);
    }

    @PostMapping("/role")
    public Role addRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    @PutMapping("/role/{id}")
    public void updateRole(@RequestBody Role newRole, @PathVariable Long id) {
        roleRepository.findById(id).map(role -> {
           role.setName(newRole.getName());

           return roleRepository.save(role);
        });
    }

    @DeleteMapping("/role/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleRepository.deleteById(id);
    }
}
