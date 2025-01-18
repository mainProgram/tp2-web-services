import {Component, inject} from '@angular/core';
import {LoaderService} from "../../services/loader.service";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatProgressSpinner} from "@angular/material/progress-spinner";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-home',
  imports: [
    MatCardTitle,
    MatCardHeader,
    MatCard,
    MatProgressSpinner,
    MatCardContent,
    NgIf
  ],
  templateUrl: './home.component.html',
  standalone: true,
  styleUrl: './home.component.sass'
})
export class HomeComponent {

}
