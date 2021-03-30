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
       List<Role> roles= roleRepository.findAll();
        for( int i = 0; i < user.getRoles().size(); i++ ){
            for( int j = 0; j < roles.size(); j++ ) {
                if(user.getRoles().get(i).getName().equals(roles.get(j).getName())){
                    Role role= roleRepository.findById(roles.get(j).getId()).get();
                    user.removeRole(user.getRoles().get(i));
                    user.assignRoles(role);
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
