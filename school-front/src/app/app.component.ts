import {Component} from '@angular/core';
import {RouterModule, RouterOutlet} from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {CommonModule} from '@angular/common';
import {MatNativeDateModule} from '@angular/material/core';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatAnchor} from "@angular/material/button";

@Component({
  selector: 'app-root',
    imports: [
        CommonModule,
        RouterOutlet,
        MatIconModule,
        RouterModule,
        MatNativeDateModule,
        MatToolbarModule,
        MatAnchor
    ],
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent {
  title = 'School-App';
}
