package com.freddygonzalez.apirest.controller;

import com.freddygonzalez.apirest.exception.ResourceNotFoundException;
import com.freddygonzalez.apirest.model.Project;
import com.freddygonzalez.apirest.model.Task  ;
import com.freddygonzalez.apirest.repository.ProjectRepository;
import com.freddygonzalez.apirest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/{projectId}/tasks")
    public List<Task> getTasksByProject(@PathVariable Long projectId) {
        // Implementar lógica para obtener tareas por ID de proyecto
        return taskRepository.findByProjectProjectId(projectId);
    }

    @PostMapping("/{projectId}/tasks")
    public Task createTask(@PathVariable Long projectId, @RequestBody Task task) {
        // Implementar lógica para crear una nueva tarea en un proyecto específico

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        task.setProject(project);


        return taskRepository.save(task);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        task.setStatus(taskDetails.getStatus());
        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskRepository.deleteById(taskId);
        return ResponseEntity.ok().build();
    }
}
