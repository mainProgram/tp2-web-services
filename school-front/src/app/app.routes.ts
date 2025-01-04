import { Routes } from '@angular/router';
import {CreateComponent} from './components/etudiant/create/create.component';
import {ListEtudiantComponent} from "./components/etudiant/list-etudiant/list-etudiant.component";
import {DetailEtudiantComponent} from "./components/etudiant/detail-etudiant/detail-etudiant.component";
import {AddEditClasseComponent} from "./components/classe/add-edit-classe/add-edit-classe.component";
import {ListClasseComponent} from "./components/classe/list-classe/list-classe.component";
import {DetailClasseComponent} from "./components/classe/detail-classe/detail-classe.component";

export const routes: Routes = [
  {
    path: 'etudiants',
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: ListEtudiantComponent,
      },
      {
        path: 'create',
        pathMatch: 'full',
        component: CreateComponent,
      },
      {
        path: 'edit/:id',
        pathMatch: 'full',
        component: CreateComponent,
      },
      {
        path: ':id',
        pathMatch: 'full',
        component: DetailEtudiantComponent,
      },
    ],
  },
  {
    path: 'professeurs',
    pathMatch: 'full',
    component: CreateComponent,
  },
  {
    path: 'matieres',
    pathMatch: 'full',
    component: CreateComponent,
  },
  {
    path: 'classes',
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: ListClasseComponent,
      },
      {
        path: 'create',
        pathMatch: 'full',
        component: AddEditClasseComponent,
      },
      {
        path: 'edit/:id',
        pathMatch: 'full',
        component: AddEditClasseComponent,
      },
      {
        path: ':id',
        pathMatch: 'full',
        component: DetailClasseComponent,
      },
    ],
  },

];
