import { Injectable, inject, signal, computed } from '@angular/core';
import { AuthApiService } from './auth-api.service';
import { RegisterRequest, LoginRequest } from '../../models/auth.model';
import { tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly authApi = inject(AuthApiService);

  // --- Estado Privado (Encapsulamiento) ---
  private readonly _token = signal<string | null>(localStorage.getItem('token'));

  // --- Exposición Pública (Solo lectura) ---
  readonly token = this._token.asReadonly();
  readonly isAuthenticated = computed(() => !!this._token());

  register(data: RegisterRequest) {
    return this.authApi.register(data).pipe(
      tap((res) => {
        localStorage.setItem('token', res.token);
        this._token.set(res.token);
      }),
    );
  }

  login(data: LoginRequest) {
    return this.authApi.login(data).pipe(
      tap((res) => {
        localStorage.setItem('token', res.token);
        this._token.set(res.token);
      }),
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    this._token.set(null);
  }
}
