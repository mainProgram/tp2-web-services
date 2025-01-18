import {Component, inject} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {take} from "rxjs";
import {ProfesseurService} from "../../../services/professeur.service";
import {MatIcon} from "@angular/material/icon";
import {MatCard, MatCardActions, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatTooltip} from "@angular/material/tooltip";
import {MatButton, MatIconButton} from "@angular/material/button";
import {NgForOf, NgIf} from "@angular/common";
import Swal from "sweetalert2";
import {LoaderService} from "../../../services/loader.service";
import {LoaderComponent} from "../../../shared/components/loader/loader.component";

@Component({
  selector: 'app-list-professeur',
  imports: [
    MatIcon,
    MatCardHeader,
    MatCard,
    MatCardTitle,
    MatCardActions,
    MatTooltip,
    MatIconButton,
    MatButton,
    NgIf,
    RouterLink,
    MatCardContent,
    NgForOf,
    LoaderComponent
  ],
  templateUrl: './list-professeur.component.html',
  standalone: true,
  styleUrl: './list-professeur.component.sass'
})
export class ListProfesseurComponent {
  router = inject(Router);
  professeurService = inject(ProfesseurService);
  loaderService = inject(LoaderService);
  loading = inject(LoaderService).loading;
  constructor() {
    this.loaderService.showLoader()
    this.professeurService.fetchProfesseurs().pipe(take(1)).subscribe({
      next: () => {this.loaderService.hideLoader()},
      error: () => {this.loaderService.hideLoader()},
    });
  }

  get professeurs() {
    return this.professeurService.resources;
  }

  deleteProfesseur(id: number){
    Swal.fire({
      title: 'Suppression',
      text: 'Voulez-vous vraiment vous supprimer le professeur ?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Supprimer',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.value) {
        this.professeurService.deleteProfesseur(id).subscribe({
          next: (data) => {
            console.log(data)
            Swal.fire(
                'Succès',
                'Suppression réussie !',
                'success'
            )
          }
        })
      }
    })
  }
}


