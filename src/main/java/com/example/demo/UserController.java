package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        for (Role role: user.getRoles()){
            for(Role name: roleRepository.findAll()){
                if(role.getName().equals(name.getName())){
                    Role roles= roleRepository.findById(name.getId()).get();
                    user.setRoles(new ArrayList<>());
                    user.assignRoles(roles);
                }
            }
        }

       User userData = userRepository.save(user);
        return ResponseEntity.ok().body(userData);
    }

    @GetMapping("/user/all")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<User> assignRole(@PathVariable Long userId,@PathVariable Long roleId){
        User user= userRepository.findById(userId).get();
        Role role= roleRepository.findById(roleId).get();

        user.assignRoles(role);


        User userData = userRepository.save(user);
        return ResponseEntity.ok().body(userData);
    }

}
