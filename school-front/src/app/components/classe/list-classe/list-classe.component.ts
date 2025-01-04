import {Component, inject} from '@angular/core';
import {ClasseService} from "../../../services/classe.service";
import {CommonModule} from "@angular/common";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatCard, MatCardActions, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {MatTooltip} from "@angular/material/tooltip";
import {Router, RouterLink} from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: 'app-list-classe',
  imports: [
    CommonModule,
    MatButton,
    MatCard,
    MatCardActions,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatIcon,
    MatIconButton,
    MatTooltip,
    RouterLink
  ],
  standalone: true,
  templateUrl: './list-classe.component.html',
  styleUrl: './list-classe.component.sass'
})
export class ListClasseComponent {
  router = inject(Router);
  private classeService = inject(ClasseService);
  classes: any[] = [];

  constructor() {
    this.classeService.getClasses().subscribe((data) => (this.classes = data));
  }

  seeDetails(id: number){
    this.router.navigateByUrl('/').then((response: any) => {
      this.router.navigateByUrl("/classes/"+id)
    })
  }

  modifierClasse(id: number){
    this.router.navigateByUrl('/').then((response: any) => {
      this.router.navigateByUrl("/classes/edit/"+id)
    })
  }

  deleteClasse(id: number){
    Swal.fire({
      title: 'Suppression',
      text: 'Voulez-vous vraiment vous supprimer la classe ?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Supprimer',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.value) {
        Swal.fire(
            'Succès',
            'Suppression réussie !',
            'success'
        ).then(()=>{
          this.classeService.deleteClasse(id).subscribe({
            next: (data) => {
              console.log(data)
            }
          })
        })
      }
    })
  }
}
