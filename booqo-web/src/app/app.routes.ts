import { Routes } from '@angular/router';
import { authguard } from '@core/guards/auth.guard';

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
    canActivate: [authguard],
  },
  {
    path: 'setup-center',
    loadComponent: () =>
      import('./features/center/pages/setup-center/setup-center.page').then(
        (m) => m.SetupCenterPage,
      ),
    canActivate: [authguard],
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
];
