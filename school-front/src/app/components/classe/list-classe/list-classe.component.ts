import {Component, inject, OnInit} from '@angular/core';
import {ClasseService} from "../../../services/classe.service";
import {CommonModule} from "@angular/common";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatCard, MatCardActions, MatCardHeader, MatCardTitle} from "@angular/material/card";
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
export class ListClasseComponent implements OnInit{
  router = inject(Router);
  private classeService = inject(ClasseService);
  classes: any[] = [];

  ngOnInit(): void {
    this.getClasses()
  }

  getClasses(){
    this.classeService.getClasses().subscribe({
      next: value => {
        console.log(value)
        this.classes = value
      }
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
        this.classeService.deleteClasse(id).subscribe({
          next: (data) => {
            Swal.fire(
                'Succès',
                'Suppression réussie !',
                'success'
            ).then(() => this.getClasses())
          }
        })
      }
    })
  }
}
