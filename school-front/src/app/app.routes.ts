import { Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {LogoutScreenComponent} from "./shared/components/logout-screen";
import {UnprotectedRouteComponent} from "./shared/components/unprotected-route";
import {ProtectedRouteComponent} from "./shared/components/protected-route";
import {LogoutRouteGuard} from "./guards/LogoutRouteGuard";
import {AuthGuard} from "./guards/AuthGuard";

export enum AppRoutes {
  Main = '',
  Protected = 'protected',
  Unprotected = 'unprotected',
  Logout = 'logout',
  NotFound = '404',
}

export const routes: Routes = [
  {
    path: AppRoutes.Protected,
    canActivate: [AuthGuard],
    component: ProtectedRouteComponent,
  },
  {
    path: AppRoutes.Unprotected,
    component: UnprotectedRouteComponent,
  },
  {
    path: AppRoutes.Logout,
    canActivate: [LogoutRouteGuard],
    component: LogoutScreenComponent,
  },
  {
    path: AppRoutes.NotFound,
    component: NotFoundComponent,
  },
  {
    path: '',
    loadComponent: () => HomeComponent
  },
  {
    path: 'etudiants',
    loadChildren: () => import('./routes/etudiant.routes').then(m => m.ETUDIANT_ROUTES),
    canActivate: [AuthGuard],
  },
  {
    path: 'professeurs',
    loadChildren: () => import('./routes/professeur.routes').then(m => m.PROFESSEUR_ROUTES),
    canActivate: [AuthGuard],
  },
  {
    path: 'matieres',
    loadChildren: () => import('./routes/matiere.routes').then(m => m.MATIERE_ROUTES),
    canActivate: [AuthGuard],
  },
  {
    path: 'classes',
    loadChildren: () => import('./routes/classe.routes').then(m => m.CLASSE_ROUTES),
    canActivate: [AuthGuard],
  },
  {
    path: '**',
    loadComponent: () => NotFoundComponent
  },
];
