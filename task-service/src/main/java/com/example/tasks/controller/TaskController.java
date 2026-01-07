package com.example.tasks.controller;

import com.example.tasks.model.Tasks;
import com.example.tasks.model.Comments; // Import Comments model
import com.example.tasks.service.TasksService; // Use the service instead of repository directly

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Import HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks/v1")
public class TaskController {

    @Autowired
    private TasksService tasksService; // Autowire the service

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Retrieves a list of all tasks in the system.")
    public List<Tasks> getAllTasks() {
        return tasksService.getAllTasks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by ID", description = "Retrieves a specific task by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the task",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tasks.class)) }),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content)
    })
    public ResponseEntity<Tasks> getTaskById(@PathVariable Long id) {
        Optional<Tasks> task = tasksService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new task", description = "Adds a new task to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tasks.class)) })
    })
    @ResponseStatus(HttpStatus.CREATED) // Set response status to 201 Created
    public Tasks createTask(@RequestBody Tasks task) {
        return tasksService.createTask(task);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing task", description = "Updates the details of a task by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tasks.class)) }),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content)
    })
    public ResponseEntity<Tasks> updateTask(@PathVariable Long id, @RequestBody Tasks taskDetails) {
        Optional<Tasks> updatedTask = tasksService.updateTask(id, taskDetails);
        return updatedTask.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task", description = "Removes a task from the system by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT) // Set response status to 204 No Content
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = tasksService.deleteTask(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskId}/comments")
    @Operation(summary = "Add a comment to a task", description = "Adds a new comment to a specified task.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comments.class)) }),
            @ApiResponse(responseCode = "404", description = "Task not found", // Assuming you'd validate taskId in service
                    content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED) // Set response status to 201 Created
    public ResponseEntity<Comments> addCommentToTask(@PathVariable Long taskId, @RequestBody Comments comment) {
        // You might want to set the task_item_id on the comment object here
        // based on the taskId from the path, if your comment object doesn't already handle it.
        // For simplicity, assuming comment.setTaskItemId(taskId) is handled in service or model setup.
        comment.setTaskItemId(taskId); // Ensure the comment is linked to the correct task
        Comments newComment = tasksService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
}