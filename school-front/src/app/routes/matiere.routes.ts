import { Routes } from '@angular/router';

export const MATIERE_ROUTES: Routes = [
    {
        path: '',
        loadComponent: () => import('../components/matiere/list-matiere/list-matiere.component').then(m => m.ListMatiereComponent)
    },
    {
        path: 'create',
        loadComponent: () => import('../components/matiere/add-edit-matiere/add-edit-matiere.component').then(m => m.AddEditMatiereComponent)
    },
    {
        path: 'edit/:id',
        loadComponent: () => import('../components/matiere/add-edit-matiere/add-edit-matiere.component').then(m => m.AddEditMatiereComponent)
    }
];