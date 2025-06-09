package com.wavey.waveyspringbootmaven;

import jakarta.validation.constraints.NotNull;

public class UsersCreateRequest {

    @NotNull
    public String firstName;
    @NotNull
    public String lastName;
    @NotNull
    public String email;
    @NotNull
    public String password;
    @NotNull
    public String profilePicture;

    public UsersCreateRequest(String firstName, String profilePicture, String password, String email, String lastName) {
        this.firstName = firstName;
        this.profilePicture = profilePicture;
        this.password = password;
        this.email = email;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




}
