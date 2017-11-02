package com.momentu.momentuapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name="user")
public class User extends AbstractEntity {

    @Column(name="username")
    private String username;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="gender")
    private String gender;

    @Column(name="password")
    private String password;

    @OneToMany(targetEntity=UserRole.class)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private List<UserRole> roles;

    public List<UserRole> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        this.password = encodedPassword;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return Objects.equals(id, ((User) obj).id);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
