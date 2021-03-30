package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name ="t_users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String mobile;

    @Column(unique = true)
    private String email;

    public void assignRoles(Role role){
        roles.add(role);
    }



    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL )
    private List<Role> roles;
}
