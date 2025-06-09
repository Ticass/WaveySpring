package com.wavey.waveyspringbootmaven;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Waves {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-incrementing primary keys
    private Integer id;
    private String firstName;
    private String lastName;
    private String content;
    private Integer userId;
    private String contentPhoto;
    private Boolean deleted;
    private Integer likes;

    public Waves() {
    }

    // This constructor should ideally NOT include 'id' if you're relying on GENERATED values,
    // but it won't cause issues for new saves if IDENTITY is used and id is null.
    public Waves(Integer id, String firstName, String lastName, String content, Integer userId, String contentPhoto, Boolean deleted, Integer likes) {
        this.id = id; // Hibernate will ignore this 'id' on a new save if IDENTITY is used.
        this.firstName = firstName;
        this.lastName = lastName;
        this.content = content;
        this.userId = userId;
        this.contentPhoto = contentPhoto;
        this.deleted = deleted;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Waves waves = (Waves) o;
        return Objects.equals(id, waves.id) && Objects.equals(firstName, waves.firstName) && Objects.equals(lastName, waves.lastName) && Objects.equals(content, waves.content) && Objects.equals(userId, waves.userId) && Objects.equals(contentPhoto, waves.contentPhoto) && Objects.equals(deleted, waves.deleted) && Objects.equals(likes, waves.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, content, userId, contentPhoto, deleted, likes);
    }
}
