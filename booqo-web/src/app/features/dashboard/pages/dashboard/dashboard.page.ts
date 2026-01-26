import { Component } from '@angular/core';
import { Navbar } from '@shared/components/navbar/navbar';

@Component({
  selector: 'app-dashboard',
  imports: [Navbar],
  templateUrl: './dashboard.page.html',
  styleUrl: './dashboard.page.css',
})
export class DashboardPage {}
