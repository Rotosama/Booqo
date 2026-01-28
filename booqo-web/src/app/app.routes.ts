import { Routes } from '@angular/router';
import { authguard } from '@core/guards/auth.guard';
import { MainLayoutComponent } from '@shared/components/main-layout/main-layout.component';
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
    path: 'setup-center',
    loadComponent: () =>
      import('./features/center/pages/setup-center/setup-center.page').then(
        (m) => m.SetupCenterPage,
      ),
    canActivate: [authguard],
  },
  {
    path: '',
    component: MainLayoutComponent,
    canActivate: [authguard],
    children: [
      {
        path: 'dashboard',
        loadComponent: () =>
          import('./features/dashboard/pages/dashboard.page').then((m) => m.DashboardPage),
      },
      {
        path: 'calendar',
        loadComponent: () =>
          import('./features/calendar/pages/calendar.page').then((m) => m.CalendarPage),
      },
      {
        path: 'clients',
        loadComponent: () =>
          import('./features/clients/pages/clients.page').then((m) => m.ClientsPage),
      },
      {
        path: 'billing',
        loadComponent: () =>
          import('./features/billing/pages/billing.page').then((m) => m.BillingPage),
      },
      {
        path: 'crm',
        loadComponent: () => import('./features/crm/pages/crm.page').then((m) => m.CrmPage),
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
    ],
  },
  {
    path: '**',
    redirectTo: 'dashboard',
  },
];
