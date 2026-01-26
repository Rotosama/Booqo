import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '@core/services/auth.service';
import { NotificationService } from '@shared/services/notification.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  private router = inject(Router);
  private authService = inject(AuthService);
  private notify = inject(NotificationService);

  onLogout() {
    this.authService.logout();
    this.notify.show('Sesión cerrada. ¡Vuelve pronto!', 'info');
    this.router.navigate(['/login']);
  }
  get userEmail() {
    return this.authService.userEmail();
  }
}
