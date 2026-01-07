package com.example.tasks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Entity
@Table(name = "task_comments")
public class Comments {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @jakarta.persistence.Column(name = "task_item_id")
    private Long taskItemId; // Changed to Long (wrapper class)

    @jakarta.persistence.Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @jakarta.persistence.Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @jakarta.persistence.Column(name = "user_name")
    private String userName;

    @jakarta.persistence.Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    // Explicit Getters and Setters for taskItemId (and others for consistency, if Lombok issues persist)
    public Long getTaskItemId() {
        return taskItemId;
    }

    public void setTaskItemId(Long taskItemId) {
        this.taskItemId = taskItemId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}