package com.mauropot.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mauropot.portfolio.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
