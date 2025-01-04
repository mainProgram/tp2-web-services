import { Injectable } from '@angular/core';
import {map, Observable} from "rxjs";
import {Apollo} from "apollo-angular";
import {gql} from "@apollo/client/core";
import {IClasseRequest} from "../models/classe/classe-request.model";

@Injectable({
  providedIn: 'root'
})
export class ClasseService {

  private PATH = "/graphql";
  private HOST = "http://localhost:8090";
  private fullUrl = this.HOST + this.PATH;

  constructor(private apollo: Apollo) { }

    getClasses(): Observable<any> {
    return this.apollo
        .watchQuery({
          query: gql`
            query {
              getAllClasses {
                id
                libelle
                professeursIds
                matieresIds
                etudiantsIds
              }
            }
          `,
          context: {
            uri: this.fullUrl,
          },
        })
        .valueChanges.pipe(map((result: any) => result.data.getAllClasses));
  }

    getClasseById(id: number): Observable<any> {
        return this.apollo
            .watchQuery({
                query: gql`
                    query GetClasseById($id: ID!) {
                      getClasseById(id: $id) {
                        id
                        libelle
                        professeursIds
                        matieresIds
                        etudiantsIds
                      }
                    }
                  `,
                context: {
                    uri: this.fullUrl,
                },
                variables: {
                    id: id,
                },
            })
            .valueChanges.pipe(map((result: any) => result.data.getClasseById));
    }

    createClasse(saveClasseDTO: IClasseRequest): Observable<any> {
        return this.apollo.mutate({
            mutation: gql`
            mutation CreateClasse($saveClasseDTO: SaveClasseDTO) {
                createClasse(saveClasseDTO: $saveClasseDTO) {
                    id
                    libelle
                    professeursIds
                    matieresIds
                    etudiantsIds
                }
            }
        `,
            context: {
                uri: this.fullUrl,
            },
            variables: {
                saveClasseDTO: saveClasseDTO,
            },
        }).pipe(map((result: any) => result.data.createClasse));
    }

    updateClasse(saveClasseDTO: IClasseRequest): Observable<any> {
        return this.apollo.mutate({
            mutation: gql`
            mutation UpdateClasse($saveClasseDTO: SaveClasseDTO) {
                updateClasse(saveClasseDTO: $saveClasseDTO) {
                    id
                    libelle
                    professeursIds
                    matieresIds
                    etudiantsIds
                }
            }
        `,
            context: {
                uri: this.fullUrl,
            },
            variables: {
                saveClasseDTO: saveClasseDTO,
            },
        }).pipe(map((result: any) => result.data.updateClasse));
    }

    deleteClasse(id: number): Observable<any> {
        return this.apollo.mutate({
            mutation: gql`
             mutation DeleteClasse($id: ID!) {
                deleteClasse(id: $id)
             }
        `,
            context: {
                uri: this.fullUrl,
            },
            variables: {
                id: id,
            },
        }).pipe(map((result: any) => result.data.deleteClasse));
    }

}
