import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';
import { AuthService } from '@core/services/auth.service';
import { NotificationService } from '@shared/services/notification.service';

export const authguard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const notify = inject(NotificationService);

  if (authService.isAuthenticated()) {
    return true;
  }

  notify.show('No tienes acceso a esta página.', 'error');
  router.navigate(['/login']);

  return false;
};
