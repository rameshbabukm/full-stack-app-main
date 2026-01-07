package com.example.tasks.service;

import com.example.tasks.model.Comments;
import com.example.tasks.model.Tasks;
import com.example.tasks.repository.CommentsRepository;
import com.example.tasks.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import Transactional

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    public Optional<Tasks> getTaskById(Long id) {
        return tasksRepository.findById(id);
    }

    public Tasks createTask(Tasks task) {
        return tasksRepository.save(task);
    }

    public Optional<Tasks> updateTask(Long id, Tasks taskDetails) {
        Optional<Tasks> optionalTask = tasksRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return Optional.empty();
        }
        Tasks task = optionalTask.get();
        task.setNumber(taskDetails.getNumber());
        task.setTitle(taskDetails.getTitle());
        task.setStatus(taskDetails.getStatus());
        task.setAssignee(taskDetails.getAssignee());
        // task.setCreatedAt(taskDetails.getCreatedAt()); // This line was removed in a previous step
        task.setDeletedAt(taskDetails.getDeletedAt());
        task.setDescription(taskDetails.getDescription());
        task.setDescriptionRichText(taskDetails.getDescriptionRichText());
        task.setDueDate(taskDetails.getDueDate());
        task.setUpdatedAt(taskDetails.getUpdatedAt());
        task.setPriority(taskDetails.getPriority());
        return Optional.of(tasksRepository.save(task));
    }

    @Transactional // Ensure atomicity for delete operations
    public boolean deleteTask(Long id) {
        if (!tasksRepository.existsById(id)) {
            return false;
        }
        // First, delete all comments associated with the task
        commentsRepository.deleteByTaskItemId(id); // Delete child records
        // Then, delete the task itself
        tasksRepository.deleteById(id);
        return true;
    }

    public Comments addComment(Comments comment) {
        // You might want to add validation here, e.g., checking if task_item_id exists
        return commentsRepository.save(comment);
    }
}