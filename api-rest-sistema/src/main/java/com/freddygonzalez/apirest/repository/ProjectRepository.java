package com.freddygonzalez.apirest.repository;

import com.freddygonzalez.apirest.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProjectRepository extends JpaRepository<Project, Long> {
}
