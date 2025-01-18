import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {ProfesseurService} from "../../../services/professeur.service";
import {IProfesseurResponse} from "../../../models/professeur/professeur-response.model";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-detail-professeur',
  imports: [
    MatCardHeader,
    MatCardContent,
    MatCardTitle,
    MatButton,
    RouterLink,
    MatCard,
    NgIf
  ],
  templateUrl: './detail-professeur.component.html',
  standalone: true,
  styleUrl: './detail-professeur.component.sass'
})
export class DetailProfesseurComponent implements OnInit{
  route = inject(ActivatedRoute)
  professeurService = inject(ProfesseurService);
  id: number | null = 0
  professeur: IProfesseurResponse | null = null

  constructor() {
  }

  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');
    if (id != null) {
      this.id = +id;
      this.professeurService.getProfesseur(this.id).subscribe({
        next: (data: IProfesseurResponse) => {
          this.professeur = data as IProfesseurResponse
        },
        error: (err) => {
          console.log(err)
        }
      })
    }
  }
}
