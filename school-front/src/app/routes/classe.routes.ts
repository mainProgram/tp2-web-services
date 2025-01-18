import { Routes } from '@angular/router';

export const CLASSE_ROUTES: Routes = [
    {
        path: '',
        loadComponent: () => import('../components/classe/list-classe/list-classe.component').then(m => m.ListClasseComponent)
    },
    {
        path: 'create',
        loadComponent: () => import('../components/classe/add-edit-classe/add-edit-classe.component').then(m => m.AddEditClasseComponent)
    },
    {
        path: 'edit/:id',
        loadComponent: () => import('../components/classe/add-edit-classe/add-edit-classe.component').then(m => m.AddEditClasseComponent)
    },
    {
        path: ':id',
        loadComponent: () => import('../components/classe/detail-classe/detail-classe.component').then(m => m.DetailClasseComponent)
    }
];
