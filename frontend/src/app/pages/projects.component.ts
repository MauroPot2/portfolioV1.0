import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // ⬅️ IMPORT NECESSARIO

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [CommonModule], // ⬅️ AGGIUNGI QUI
  templateUrl: './projects.component.html'
})
export class ProjectsComponent {
  projects = [
    {
      title: 'CicloVerso WebApp',
      description: "Piattaforma per la gestione di un'officina ciclistica, prenotazioni e slot.",
      image: 'assets/images/music-player.png',
      tags: ['Python', 'Flask', 'Bootstrap', 'Sqlite'],
      link: 'https://github.com/MauroPot2/cicloversoWebApp'
    },
    {
      title: 'Bike Tracker',
      description: 'App per tracciare percorsi ciclistici.',
      image: 'assets/images/bike-tracker.png',
      tags: ['Angular', 'Leaflet', 'Firebase'],
      link: 'https://github.com/mauropot/bike-tracker'
    },
    {
      title: 'Star Wars API Explorer',
      description: 'Esplora dati dell’universo Star Wars.',
      image: 'assets/images/swapi-explorer.png',
      tags: ['Angular', 'REST API', 'RxJS'],
      link: 'https://github.com/mauropot/swapi-explorer'
    },
    {
      title: 'Youtube Mp3 Converter',
      description: 'Convertitore da YouTube a MP3.',
      image: 'assets/images/youtube-mp3-converter.png',
      tags: ['Python', 'ffmpeg', 'pytubefix'],
      link:"https://github.com/MauroPot2/potube"
    }
  ];
}
