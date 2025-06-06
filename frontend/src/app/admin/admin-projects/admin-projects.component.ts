import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectService, Project } from '../../services/project.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-projects',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-projects.component.html'
})
export class AdminProjectsComponent implements OnInit {
  projects: Project[] = [];
  selectedProject: Project = this.emptyProject();

  constructor(private projectService: ProjectService) {}

  ngOnInit() {
    this.loadProjects();
  }

  loadProjects() {
    this.projectService.getProjects().subscribe({
      next: data => this.projects = data,
      error: err => console.error('Errore nel caricamento', err)
    });
  }

  emptyProject(): Project {
    return { title: '', description: '', image: '', tags: [], link: '' };
  }

  selectProject(project: Project) {
    this.selectedProject = { ...project }; // copia profonda
  }

  saveProject() {
    if (this.selectedProject.id) {
      // update
      this.projectService.updateProject(this.selectedProject).subscribe({
        next: () => this.loadProjects()
      });
    } else {
      // create
      this.projectService.createProject(this.selectedProject).subscribe({
        next: () => this.loadProjects()
      });
    }
    this.selectedProject = this.emptyProject();
  }

  deleteProject(id: number) {
    if (confirm("Sei sicuro di voler cancellare questo progetto?")) {
      this.projectService.deleteProject(id).subscribe({
        next: () => this.loadProjects()
      });
    }
  }

  cancel() {
    this.selectedProject = this.emptyProject();
  }
}
