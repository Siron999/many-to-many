package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoleController {

    private final RoleRepository roleRepository;

    @PostMapping("/role/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role roleData = roleRepository.save(role);
        return ResponseEntity.ok().body(roleData);
    }

    @GetMapping("/role/all")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
