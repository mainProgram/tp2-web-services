import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ProfesseurService} from "../../../services/professeur.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {MatDatepicker, MatDatepickerModule, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatFormField, MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {MatInput, MatInputModule} from "@angular/material/input";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatNativeDateModule} from "@angular/material/core";
import {CommonModule} from "@angular/common";
import {MatIcon} from "@angular/material/icon";
import {MatTooltip} from "@angular/material/tooltip";

@Component({
  selector: 'app-add-edit-professeur',
  imports: [MatDatepickerToggle,
    MatLabel,
    MatFormField,
    MatDatepicker,
    MatSelect,
    MatOption,
    ReactiveFormsModule,
    MatInput,
    MatButton,
    MatNativeDateModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
    CommonModule,
    MatIcon,
    MatIconButton,
    RouterLink,
    MatTooltip],
  templateUrl: './add-edit-professeur.component.html',
  standalone: true,
  styleUrl: './add-edit-professeur.component.sass'
})
export class AddEditProfesseurComponent {
  professeurForm: FormGroup;
  professeurId: number | null = null;
  professeurService = inject(ProfesseurService);
  router = inject(Router);
  route = inject(ActivatedRoute);

  constructor(private fb: FormBuilder) {

    this.professeurForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      telephone: ['', Validators.required],
      specialite: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.professeurForm.valid) {
      if (this.professeurId) {
        this.professeurService.updateProfesseur(this.professeurId, this.professeurForm.value).subscribe({
          next: (data : any) => {
            this.router.navigateByUrl('/').then((response: any) => {
              this.router.navigateByUrl("/professeurs/" + data.id)
            })
          },
          error: (err) => {
            console.error("Erreur lors de la mise à jour:", err);
          }
        });
      }
      else {
        this.professeurService.addProfesseur(this.professeurForm.value).subscribe({
          next: (data: any) => {
            this.router.navigateByUrl('/').then((response: any) => {
              this.router.navigateByUrl("/professeurs/" + data.id)
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

  public get f(){  return this.professeurForm.controls }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.professeurId = +id;
      this.professeurService.getProfesseur(this.professeurId).subscribe({
        next: (professeur) => {
          console.log(professeur)
          this.professeurForm.patchValue(professeur as { [key: string]: any });
        },
        error: (err) => {
          console.error("Erreur lors du chargement du professeur :", err);
        }
      });
    }
  }
}

