import {Component, inject} from '@angular/core';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatListModule} from "@angular/material/list";
import {CommonModule} from "@angular/common";
import {Router, RouterLink} from "@angular/router";
import {EtudiantService} from "../../../services/etudiant.service";
import {finalize, take} from "rxjs";
import Swal from 'sweetalert2';
import {MatTooltip} from "@angular/material/tooltip";
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardTitle
} from "@angular/material/card";
import {LoaderService} from "../../../services/loader.service";
import {LoaderComponent} from "../../../shared/components/loader/loader.component";
@Component({
  selector: 'app-list-etudiant',
  imports: [
    CommonModule, MatListModule, MatButtonModule, MatIconModule, MatTooltip, RouterLink,
    MatCardContent, MatCardActions, MatCardTitle, MatCardHeader, MatCard, LoaderComponent
  ],
  standalone: true,
  templateUrl: './list-etudiant.component.html',
  styleUrl: './list-etudiant.component.sass'
})
export class ListEtudiantComponent {
  router = inject(Router);
  etudiantService = inject(EtudiantService);
  loaderService = inject(LoaderService);
  loading = inject(LoaderService).loading;

  constructor() {
    this.loaderService.showLoader()
    this.etudiantService.fetchEtudiants().pipe(
      take(1),
      finalize(() => this.loaderService.hideLoader())
    ).subscribe();
  }

  get etudiants() {
    return this.etudiantService.resources;
  }

  deleteEtudiant(id: number){
    Swal.fire({
      title: 'Suppression',
      text: 'Voulez-vous vraiment vous supprimer le etudiant ?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Supprimer',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.value) {
        this.etudiantService.deleteEtudiant(id).subscribe({
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

