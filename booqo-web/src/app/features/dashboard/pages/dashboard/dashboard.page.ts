import { Component, OnInit, inject } from '@angular/core';
import { Navbar } from '@shared/components/navbar/navbar';
import { CenterService } from '@core/services/center.service';
import { signal } from '@angular/core';
import { MainLayout } from '@shared/components/main-layout/main-layout';
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [Navbar, MainLayout],
  templateUrl: './dashboard.page.html',
  styleUrl: './dashboard.page.css',
})
export class DashboardPage implements OnInit {
  private centerService = inject(CenterService);

  centerName = signal<string>('Cargando...');
  id = signal<number>(0);
  ngOnInit(): void {
    this.centerService.getMyCenter().subscribe({
      next: (center) => {
        this.centerName.set(center.name);
      },
    });
  }
}
