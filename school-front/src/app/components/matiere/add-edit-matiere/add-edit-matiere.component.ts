import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatiereService} from "../../../services/matiere.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {MatIcon} from "@angular/material/icon";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {NgIf} from "@angular/common";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatTooltip} from "@angular/material/tooltip";
import {LoaderService} from "../../../services/loader.service";

@Component({
  selector: 'app-add-edit-matiere',
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
  templateUrl: './add-edit-matiere.component.html',
  standalone: true,
  styleUrl: './add-edit-matiere.component.sass'
})
export class AddEditMatiereComponent  implements OnInit{
  matiereForm: FormGroup;
  matiereId: number | null = null;
  matiereService = inject(MatiereService);
  router = inject(Router);
  route = inject(ActivatedRoute);
  loaderService = inject(LoaderService);
  loading = inject(LoaderService).loading;

  constructor(private fb: FormBuilder) {
    this.matiereForm = this.fb.group({
      libelle: ['', Validators.required],
      coefficient: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
    this.loaderService.showLoader()
    if (id) {
      this.matiereId = +id;
      this.matiereService.getMatiere(this.matiereId).subscribe({
        next: (matiere) => {
          console.log(matiere)
          this.loaderService.hideLoader()
          this.matiereForm.get("libelle")?.setValue(matiere.libelle);
          this.matiereForm.get("coefficient")?.setValue(matiere.coefficient);
        },
        error: (err) => {
          this.loaderService.hideLoader()
          console.error("Erreur lors du chargement de la matiere :", err);
        }
      });
    }
  }

  onSubmit(): void {
    this.loaderService.showLoader()
    if (this.matiereForm.valid) {
      if (this.matiereId) {
        console.log(this.matiereId)
        this.matiereService.updateMatiere({...this.matiereForm.value, id: this.matiereId}).subscribe({
          next: (data : any) => {
            console.log(data)
            this.loaderService.hideLoader()
            this.router.navigateByUrl('/').then((response: any) => {
              this.router.navigateByUrl("/matieres")
            })
          },
          error: (err) => {
            this.loaderService.hideLoader()
            console.error("Erreur lors de la mise à jour:", err);
          }
        });
      }
      else {
        this.matiereService.createMatiere(this.matiereForm.value).subscribe({
          next: (data: any) => {
            console.log(data)
            this.loaderService.hideLoader()
            this.router.navigateByUrl('/').then((response: any) => {
              this.router.navigateByUrl("/matieres")
            })
          },
          error: (err) => {
            this.loaderService.hideLoader()
            console.log(err)
          }
        })
      }
    } else {
      console.error('Formulaire invalide');
    }
  }

  public get f(){  return this.matiereForm.controls }
}

