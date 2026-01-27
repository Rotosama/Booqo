import { Component, inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { CenterService } from '@core/services/center.service';
import { AuthService } from '@core/services/auth.service';
import { Router } from '@angular/router';
import { NotificationService } from '@shared/services/notification.service';
import { CenterRequest } from '@core/models/center.model';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-setup-center',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './setup-center.page.html',
  styleUrl: './setup-center.page.css',
})
export class SetupCenterPage {
  private fb = inject(FormBuilder);
  private centerService = inject(CenterService);
  private authService = inject(AuthService);
  private router = inject(Router);
  private notify = inject(NotificationService);

  centerForm = this.fb.group({
    // Las validaciones van juntas en un array []
    name: ['', [Validators.required, Validators.minLength(3)]],
    cif: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9)]],
    address: ['', [Validators.required, Validators.minLength(3)]],
    phone: ['', [Validators.required, Validators.minLength(9), Validators.maxLength(9)]],
    email: ['', [Validators.required, Validators.email]],
  });

  onSubmit() {
    if (this.centerForm.valid) {
      const request: CenterRequest = this.centerForm.value as CenterRequest;
      this.centerService.createCenter(request).subscribe({
        next: (res) => {
          this.notify.show('Centro creado correctamente', 'success');
          // IMPORTANTE: Hacemos logout para que al volver a entrar el token sea el nuevo (con hasCenter: true)
          this.authService.logout();
          this.router.navigate(['/login']);
        },
        error: (err) => {
          this.notify.show('Error al crear el centro', 'error');
        },
      });
    }
  }
}
