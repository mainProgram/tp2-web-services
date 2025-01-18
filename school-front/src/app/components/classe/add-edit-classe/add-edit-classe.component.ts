import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {ClasseService} from "../../../services/classe.service";
import {MatIcon} from "@angular/material/icon";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {NgIf} from "@angular/common";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";

@Component({
  selector: 'app-add-edit-classe',
  imports: [
    MatIcon,
    MatLabel,
    MatFormField,
    ReactiveFormsModule,
    MatInput,
    NgIf,
    MatButton,
    MatIconButton,
    RouterLink,
    MatTooltip
  ],
  templateUrl: './add-edit-classe.component.html',
  standalone: true,
  styleUrl: './add-edit-classe.component.sass'
})
export class AddEditClasseComponent implements OnInit{
  classeForm: FormGroup;
  classeId: number | null = null;
  classeService = inject(ClasseService);
  router = inject(Router);
  route = inject(ActivatedRoute);

  constructor(private fb: FormBuilder) {
    this.classeForm = this.fb.group({
      libelle: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.classeId = +id;
      this.classeService.getClasseById(this.classeId).subscribe({
        next: (classe) => {
          console.log(classe)
          this.classeForm.get("libelle")?.setValue(classe.libelle);
        },
        error: (err) => {
          console.error("Erreur lors du chargement de la classe :", err);
        }
      });
    }
  }

  onSubmit(): void {
    if (this.classeForm.valid) {
      if (this.classeId) {
        console.log(this.classeId)
        this.classeService.updateClasse(+this.classeId, {...this.classeForm.value, etudiantsIds:null, matieresIds: null, professeursIds:null}).subscribe({
          next: (data : any) => {
            this.router.navigateByUrl('/').then((response: any) => {
              this.router.navigateByUrl("/classes")
            })
          },
          error: (err) => {
            console.error("Erreur lors de la mise à jour:", err);
          }
        });
      }
      else {
        this.classeService.createClasse(this.classeForm.value).subscribe({
          next: (data: any) => {
            this.router.navigateByUrl('/').then((response: any) => {
              this.router.navigateByUrl("/classes")
            })
          },
          error: (err) => {
            console.log(err)
          }
        })
      }
    } else {
      console.error('Formulaire invalide');
    }
  }

  public get f(){  return this.classeForm.controls }
}
