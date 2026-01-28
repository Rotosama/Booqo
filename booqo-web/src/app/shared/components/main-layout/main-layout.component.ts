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
  PanelLeftDashedIcon,
} from 'lucide-angular';

@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [RouterLink, RouterOutlet, LucideAngularModule],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css',
})
export class MainLayoutComponent {
  userIcon = UserIcon;
  calendarIcon = CalendarIcon;
  fileIcon = FileIcon;
  briefcaseIcon = BriefcaseIcon;
  barChartIcon = ChartAreaIcon;
  settingsIcon = SettingsIcon;
  logoutIcon = LogOut;
  dashboardIcon = PanelLeftDashedIcon;
  router = inject(Router);
  authService = inject(AuthService);
  userEmail = this.authService.userEmail;
  userRole = this.authService.userRole;

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
