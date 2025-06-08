package com.wavey.waveyspringbootmaven;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Waves {

    @Id
    private Integer id;
    private String first_name;
    private String last_name;
    private String content;
    private Integer user_id;
    private String content_photo;
    private Boolean deleted;
    private Integer likes;

    public Waves() {
    }

    public Waves(Integer id, String first_name, String last_name, String content, Integer user_id, String content_photo, Boolean deleted, Integer likes) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.content = content;
        this.user_id = user_id;
        this.content_photo = content_photo;
        this.deleted = deleted;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getContent_photo() {
        return content_photo;
    }

    public void setContent_photo(String content_photo) {
        this.content_photo = content_photo;
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
        return Objects.equals(id, waves.id) && Objects.equals(first_name, waves.first_name) && Objects.equals(last_name, waves.last_name) && Objects.equals(content, waves.content) && Objects.equals(user_id, waves.user_id) && Objects.equals(content_photo, waves.content_photo) && Objects.equals(deleted, waves.deleted) && Objects.equals(likes, waves.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, content, user_id, content_photo, deleted, likes);
    }
}
