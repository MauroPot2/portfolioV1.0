import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectCardComponent } from '../components/project-card/project-card.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ProjectCardComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  projects = [
    {
      title: 'CicloVerso',
      tech: ['Flask', 'FullCalendar', 'Docker'],
      link: 'https://github.com/MauroPot2/cicloversoWebApp'
    },
    {
      title: 'Potube',
      tech: ['Python', 'CLI', 'FFmpeg'],
      link: 'https://github.com/MauroPot2/potube'
    }
  ];
}
