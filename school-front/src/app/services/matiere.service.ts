import {inject, Injectable} from '@angular/core';
import {map, Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";

export interface Matiere {
  id?: number;
  libelle: string;
  coefficient: number;
}

@Injectable({
  providedIn: 'root'
})

export class MatiereService{

  private readonly baseUrl = 'http://localhost:8090/service/soap-api';
  private readonly NAMESPACE = 'http://groupeisi.com/matiere';
  http = inject(HttpClient);

  constructor() { }

  getAllMatieres(): Observable<Matiere[]> {
    const body = `
      <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="${this.NAMESPACE}">
         <soapenv:Header/>
         <soapenv:Body>
            <sch:GetAllMatieresRequest/>
         </soapenv:Body>
      </soapenv:Envelope>`;

    return this.callSoapService(body, 'getAllMatieres').pipe(
        map(response => this.extractMatieres(response))
    );
  }

  deleteMatiere(id: number): Observable<string> {
    console.log(id)
    const body = `
      <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="${this.NAMESPACE}">
         <soapenv:Header/>
         <soapenv:Body>
            <sch:DeleteMatiereRequest>
               <sch:id>${id}</sch:id>
            </sch:DeleteMatiereRequest>
         </soapenv:Body>
      </soapenv:Envelope>`;

    return this.callSoapService(body, 'deleteMatiere').pipe(
        map(response => {
          console.log(response)
          const parser = new DOMParser();
          const xmlDoc = parser.parseFromString(response, 'text/xml');
          return xmlDoc.getElementsByTagName('ns2:status')[0].textContent || '';
        })
    );
  }

  private callSoapService(body: string, action: string): Observable<string> {
    const headers = new HttpHeaders({
      'Content-Type': 'text/xml;charset=UTF-8',
      'SOAPAction': `${this.NAMESPACE}/${action}`
    });

    return this.http.post(this.baseUrl, body, {
      headers,
      responseType: 'text'
    });
  }

  private extractMatieres(response: string): Matiere[] {
    console.log(response)
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(response, 'text/xml');
    const matiereElements = xmlDoc.getElementsByTagName('ns2:matiere');
    const matieres: Matiere[] = [];

    for (let i = 0; i < matiereElements.length; i++) {
      const element = matiereElements[i];
      matieres.push({
        id: parseInt(element.getElementsByTagName('ns2:id')[0].textContent || '0', 10),
        libelle: element.getElementsByTagName('ns2:libelle')[0].textContent || '',
        coefficient: parseFloat(element.getElementsByTagName('ns2:coefficient')[0].textContent || '0')
      });
    }

    return matieres;
  }

  private extractMatiere(response: string): Matiere {
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(response, 'text/xml');

    return {
      id: parseInt(xmlDoc.getElementsByTagName('id')[0].textContent || '0', 10),
      libelle: xmlDoc.getElementsByTagName('libelle')[0].textContent || '',
      coefficient: parseFloat(xmlDoc.getElementsByTagName('coefficient')[0].textContent || '0')
    };
  }

}
