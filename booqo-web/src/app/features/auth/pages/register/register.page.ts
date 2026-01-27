import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';
import { NotificationService } from '../../../../shared/services/notification.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './register.page.html',
  styleUrl: './register.page.css',
})
export class RegisterPage {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);
  private notify = inject(NotificationService);

  registerForm: FormGroup = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(3)]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
  });

  onSubmit() {
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value).subscribe({
        next: () => {
          this.notify.show('¡Cuenta creada con éxito!', 'success');

          // 🚀 Decidimos la ruta basándonos en el token recién guardado
          if (this.authService.hasCenter()) {
            this.router.navigate(['/dashboard']);
          } else {
            this.router.navigate(['/setup-center']); // <-- Aquí es donde irá el usuario nuevo
          }
        },
        error: (err) => {
          // Si el back devuelve el error de "El email ya existe", aquí lo capturas
          const errorMsg = err.error || 'Error al registrar usuario.';
          this.notify.show(errorMsg, 'error');
        },
      });
    }
  }
}
