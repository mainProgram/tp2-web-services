import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'etudiants',
    loadChildren: () => import('./routes/etudiant.routes').then(m => m.ETUDIANT_ROUTES)
  },
  {
    path: 'professeurs',
    loadChildren: () => import('./routes/professeur.routes').then(m => m.PROFESSEUR_ROUTES)
  },
  // {
  //   path: 'matieres',
  //   loadComponent: () => import('./components/matiere/create/create.component').then(m => m.CreateComponent)
  // },
  {
    path: 'classes',
    loadChildren: () => import('./routes/classe.routes').then(m => m.CLASSE_ROUTES)
  }

];
