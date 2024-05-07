package com.freddygonzalez.apirest.controller;

import com.freddygonzalez.apirest.exception.ResourceNotFoundException;
import com.freddygonzalez.apirest.model.Project ;
import com.freddygonzalez.apirest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody Project projectDetails) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        Project updatedProject = projectRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        projectRepository.deleteById(projectId);
        return ResponseEntity.ok().build();
    }
}
