package com.freddygonzalez.apirest.repository;

import com.freddygonzalez.apirest.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectProjectId(Long projectId);
}