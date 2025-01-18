import { Routes } from '@angular/router';

export const PROFESSEUR_ROUTES: Routes = [
    {
        path: '',
        loadComponent: () => import('../components/professeur/list-professeur/list-professeur.component').then(m => m.ListProfesseurComponent)
    },
    // {
    //     path: 'create',
    //     loadComponent: () => import('../components/professeur/add-edit/add-edit.component').then(m => m.CreateComponent)
    // },
    // {
    //     path: 'edit/:id',
    //     loadComponent: () => import('../components/professeur/add-edit/add-edit.component').then(m => m.CreateComponent)
    // },
    // {
    //     path: ':id',
    //     loadComponent: () => import('../components/professeur/detail-professeur/detail-professeur.component').then(m => m.DetailEtudiantComponent)
    // }
];