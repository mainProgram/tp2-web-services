import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {ProfesseurService} from "../../../services/professeur.service";
import {IProfesseurResponse} from "../../../models/professeur/professeur-response.model";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {NgIf} from "@angular/common";
import {LoaderComponent} from "../../../shared/components/loader/loader.component";
import {LoaderService} from "../../../services/loader.service";

@Component({
  selector: 'app-detail-professeur',
    imports: [
        MatCardHeader,
        MatCardContent,
        MatCardTitle,
        MatButton,
        RouterLink,
        MatCard,
        NgIf,
        LoaderComponent
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
    loaderService = inject(LoaderService);
    loading = inject(LoaderService).loading;

  constructor() {
  }

  ngOnInit() {
      this.loaderService.showLoader()
    let id = this.route.snapshot.paramMap.get('id');
    if (id != null) {
      this.id = +id;
      this.professeurService.getProfesseur(this.id).subscribe({
        next: (data: IProfesseurResponse) => {
          this.professeur = data as IProfesseurResponse
            this.loaderService.hideLoader()
        },
        error: (err) => {
          console.log(err)
            this.loaderService.hideLoader()
        }
      })
    }
  }
}
