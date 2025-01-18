import {Component, inject, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {MatButton} from '@angular/material/button';
import {IEtudiantResponse} from "../../../models/etudiant/etudiant-response.model";
import {EtudiantService} from "../../../services/etudiant.service";
import {IApiResponse} from "../../../models/api-response";
import {LoaderService} from "../../../services/loader.service";
import {LoaderComponent} from "../../../shared/components/loader/loader.component";
@Component({
  selector: 'app-detail-etudiant',
  imports: [
    CommonModule,
    MatCardModule,
    MatListModule,
    MatButton,
    RouterLink,
    LoaderComponent
  ],
  standalone: true,
  templateUrl: './detail-etudiant.component.html',
  styleUrl: './detail-etudiant.component.sass'
})
export class DetailEtudiantComponent implements OnInit{
  route = inject(ActivatedRoute)
  etudiantService = inject(EtudiantService);
  id: number|null = 0
  etudiant: IEtudiantResponse|null = null
  loaderService = inject(LoaderService);
  loading = inject(LoaderService).loading;
  constructor() {
  }

  ngOnInit(){
    let id = this.route.snapshot.paramMap.get('id');
    this.loaderService.showLoader()
    if(id != null){
      this.id = +id;
      this.etudiantService.getEtudiant(this.id).subscribe({
        next: (data: IApiResponse) => {
          this.etudiant = data.data as IEtudiantResponse
          this.loaderService.hideLoader()
        },
        error: (err) => {
          this.loaderService.hideLoader()
          console.log(err)
        }
      })
    }
  }
}
