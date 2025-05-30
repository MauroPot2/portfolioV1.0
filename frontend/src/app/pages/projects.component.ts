import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectService, Project } from '../services/project.service'; // ← assicurati che il path sia corretto

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './projects.component.html'
})
export class ProjectsComponent implements OnInit {
  projects: Project[] = [];

  constructor(private projectService: ProjectService) {}

  ngOnInit(): void {
    this.projectService.getProjects().subscribe({
      next: (data) => {
        this.projects = data;
        console.log('Progetti ricevuti:', this.projects);
      },
      error: (error) => {
        console.error('Errore nel recupero dei progetti:', error);
      }
    });
  }
}
