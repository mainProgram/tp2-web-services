import { Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => HomeComponent
  },
  {
    path: 'etudiants',
    loadChildren: () => import('./routes/etudiant.routes').then(m => m.ETUDIANT_ROUTES)
  },
  {
    path: 'professeurs',
    loadChildren: () => import('./routes/professeur.routes').then(m => m.PROFESSEUR_ROUTES)
  },
  {
    path: 'matieres',
    loadChildren: () => import('./routes/matiere.routes').then(m => m.MATIERE_ROUTES)
  },
  {
    path: 'classes',
    loadChildren: () => import('./routes/classe.routes').then(m => m.CLASSE_ROUTES)
  },
  {
    path: '**',
    loadComponent: () => NotFoundComponent
  },
];
