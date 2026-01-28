import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '@core/services/auth.service';
import {
  LucideAngularModule,
  UserIcon,
  CalendarIcon,
  FileIcon,
  BriefcaseIcon,
  ChartAreaIcon,
  SettingsIcon,
  LogOut,
} from 'lucide-angular';

@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [RouterLink, RouterOutlet, LucideAngularModule],
  templateUrl: './main-layout.html',
  styleUrl: './main-layout.css',
})
export class MainLayout {
  userIcon = UserIcon;
  calendarIcon = CalendarIcon;
  fileIcon = FileIcon;
  briefcaseIcon = BriefcaseIcon;
  barChartIcon = ChartAreaIcon;
  settingsIcon = SettingsIcon;
  logoutIcon = LogOut;
  router = inject(Router);
  authService = inject(AuthService);
  userEmail = this.authService.userEmail;
  userRole = this.authService.userRole;

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
