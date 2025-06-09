package com.wavey.waveyspringbootmaven;

import jakarta.validation.constraints.NotNull;

public class WaveCreateRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String content;

    @NotNull
    private Integer userId;


    private String contentPhoto;

    @NotNull
    private Boolean deleted = false;

    @NotNull
    private Integer likes = 0;

    public WaveCreateRequest() {
        // Default constructor
    }

    public WaveCreateRequest(String firstName, String lastName, String content, Integer userId,
                             String contentPhoto, Boolean deleted, Integer likes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.content = content;
        this.userId = userId;
        this.contentPhoto = contentPhoto;
        this.deleted = deleted;
        this.likes = likes;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContentPhoto() {
        return contentPhoto;
    }

    public void setContentPhoto(String contentPhoto) {
        this.contentPhoto = contentPhoto;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
