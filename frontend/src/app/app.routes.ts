import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home.component';
import { ProjectsComponent } from './pages/projects.component';
import { AboutComponent } from './pages/about.component';


export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'projects', component: ProjectsComponent },
  { path: 'about', component: AboutComponent },

];
