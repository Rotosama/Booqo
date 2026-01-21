import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'register',
    loadComponent: () =>
      import('./features/auth/pages/register/register.page').then((m) => m.RegisterPage),
  },
  {
    path: 'login',
    loadComponent: () => import('./features/auth/pages/login/login.page').then((m) => m.LoginPage),
  },
  {
    path: 'dashboard',
    loadComponent: () =>
      import('./features/dashboard/pages/dashboard/dashboard.page').then((m) => m.DashboardPage),
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
];
