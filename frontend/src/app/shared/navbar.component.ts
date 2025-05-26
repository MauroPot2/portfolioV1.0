import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule], // ✅ fondamentale
  templateUrl: './navbar.component.html'
})
export class NavbarComponent { }
