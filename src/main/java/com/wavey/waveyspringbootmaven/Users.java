package com.wavey.waveyspringbootmaven;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
@Entity
public class Users {
    @Id
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String profilePicture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfile_picture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Users() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(first_name, users.first_name) && Objects.equals(last_name, users.last_name) && Objects.equals(email, users.email) && Objects.equals(password, users.password) && Objects.equals(profilePicture, users.profilePicture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email, password, profilePicture);
    }
}
