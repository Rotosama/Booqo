import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '@core/services/auth.service';
@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './main-layout.html',
  styleUrl: './main-layout.css',
})
export class MainLayout {
  router = inject(Router);
  authService = inject(AuthService);
  userEmail = this.authService.userEmail;
  userRole = this.authService.userRole;

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
