package com.wavey.waveyspringbootmaven;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer waveId;
    public Integer userId;
    public Boolean deleted;

    public Likes(Integer id, Integer waveId, Boolean deleted, Integer userId) {
        this.id = id;
        this.waveId = waveId;
        this.deleted = deleted;
        this.userId = userId;
    }

    public Likes() {

    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Likes likes = (Likes) o;
        return Objects.equals(id, likes.id) && Objects.equals(waveId, likes.waveId) && Objects.equals(userId, likes.userId) && Objects.equals(deleted, likes.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, waveId, userId, deleted);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWaveId() {
        return waveId;
    }

    public void setWaveId(Integer waveId) {
        this.waveId = waveId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
