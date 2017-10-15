package com.momentu.momentuapi.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class User extends AbstractEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String password;

    @OneToMany
    @JoinColumn(name = "userId", referencedColumnName = "id")
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
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(id, ((User) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
