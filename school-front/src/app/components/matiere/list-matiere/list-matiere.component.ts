import {Component, inject, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {MatiereService} from "../../../services/matiere.service";
import Swal from "sweetalert2";
import {CommonModule} from "@angular/common";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatCard, MatCardActions, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {MatTooltip} from "@angular/material/tooltip";

@Component({
  selector: 'app-list-matiere',
  imports: [
    CommonModule,
    MatButton,
    MatCard,
    MatCardActions,
    MatCardHeader,
    MatCardTitle,
    MatIcon,
    MatIconButton,
    MatTooltip,
    RouterLink
  ],
  templateUrl: './list-matiere.component.html',
  standalone: true,
  styleUrl: './list-matiere.component.sass'
})
export class ListMatiereComponent implements OnInit{
  router = inject(Router);
  private matiereService = inject(MatiereService);
  matieres: any[] = [];

  ngOnInit(): void {
    this.getMatieres()
  }

  getMatieres(){
    this.matiereService.getAllMatieres().subscribe({
      next: value => {
        console.log(value)
        this.matieres = value
      }
    })
  }

  seeDetails(id: number){
    this.router.navigateByUrl('/').then((response: any) => {
      this.router.navigateByUrl("/matieres/"+id)
    })
  }

  modifierMatiere(id: number){
    this.router.navigateByUrl('/').then((response: any) => {
      this.router.navigateByUrl("/matieres/edit/"+id)
    })
  }

  deleteMatiere(id: number){
    Swal.fire({
      title: 'Suppression',
      text: 'Voulez-vous vraiment vous supprimer la matiere ?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Supprimer',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.value) {
        this.matiereService.deleteMatiere(id).subscribe({
          next: (data) => {
            if(data == "failed"){
              Swal.fire(
                  'Erreur',
                  'Suppression échouée !',
                  'error'
              )
            }else{
              Swal.fire(
                  'Succès',
                  'Suppression réussie !',
                  'success'
              ).then(() => this.getMatieres())
            }
          }
        })
      }
    })
  }
}