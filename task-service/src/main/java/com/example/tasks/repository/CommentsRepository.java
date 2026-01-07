package com.example.tasks.repository;

import com.example.tasks.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; // Import Transactional

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Transactional // Ensures the deletion operation is atomic
    void deleteByTaskItemId(Long taskItemId);
}