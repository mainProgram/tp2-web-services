import { Injectable } from '@angular/core';
import {ResourceService} from './resource.service';
import {map, tap} from 'rxjs';
import {IProfesseurResponse} from '../models/professeur/professeur-response.model';
import {IProfesseurRequest} from '../models/professeur/professeur-request.model';
let baseUrl = "http://localhost:8090/api/v1/professeurs"

@Injectable({
  providedIn: 'root'
})
export class ProfesseurService extends ResourceService<IProfesseurResponse>{

  fetchProfesseurs() {
    return this.http
      .get<IProfesseurResponse[]>(baseUrl)
      .pipe(
        map((response) => response ),
        tap(this.setResources.bind(this))
      );
  }

  addProfesseur(professeur: IProfesseurRequest) {
    return this.http
      .post<IProfesseurResponse[]>(baseUrl, professeur)
      .pipe(
        map((response) => response as IProfesseurResponse[]),
        tap(this.setResources.bind(this))
      );
  }

  getProfesseur(id: number) {
    return this.http
      .get<IProfesseurResponse>(baseUrl+ "/"+ id)
  }

  deleteProfesseur(id: number) {
    return this.http
      .delete<IProfesseurResponse>(`${baseUrl}/${id}`)
      .pipe(tap(() => this.removeResource(id)));
  }

  updateProfesseur(id:number, professeur: IProfesseurRequest) {
    return this.http
      .put<IProfesseurResponse[]>(`${baseUrl}/${id}`, professeur)
      .pipe(
        map((response) => response as IProfesseurResponse[]),
        tap(this.setResources.bind(this))
      );
  }
}
