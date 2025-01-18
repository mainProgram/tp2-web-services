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
    NgForOf
  ],
  templateUrl: './list-professeur.component.html',
  standalone: true,
  styleUrl: './list-professeur.component.sass'
})
export class ListProfesseurComponent {
  router = inject(Router);
  professeurService = inject(ProfesseurService);

  constructor() {
    this.professeurService.fetchProfesseurs().pipe(take(1)).subscribe();
  }

  get professeurs() {
    return this.professeurService.resources;
  }
  
  seeDetails(id: number){
    this.router.navigateByUrl('/').then((response: any) => {
      this.router.navigateByUrl("/professeurs/"+id)
    })
  }

  modifierProfesseur(id: number){
    this.router.navigateByUrl('/').then((response: any) => {
      this.router.navigateByUrl("/professeurs/edit/"+id)
    })
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


