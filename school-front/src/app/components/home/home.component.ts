import {Component} from '@angular/core';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";

@Component({
  selector: 'app-home',
  imports: [
    MatCardTitle,
    MatCardHeader,
    MatCard,
    MatCardContent
  ],
  templateUrl: './home.component.html',
  standalone: true,
  styleUrl: './home.component.sass'
})
export class HomeComponent {

}
