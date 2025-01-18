import { Routes } from '@angular/router';

export const ETUDIANT_ROUTES: Routes = [
    {
        path: '',
        loadComponent: () => import('../components/etudiant/list-etudiant/list-etudiant.component').then(m => m.ListEtudiantComponent)
    },
    {
        path: 'create',
        loadComponent: () => import('../components/etudiant/create/create.component').then(m => m.CreateComponent)
    },
    {
        path: 'edit/:id',
        loadComponent: () => import('../components/etudiant/create/create.component').then(m => m.CreateComponent)
    },
    {
        path: ':id',
        loadComponent: () => import('../components/etudiant/detail-etudiant/detail-etudiant.component').then(m => m.DetailEtudiantComponent)
    }
];