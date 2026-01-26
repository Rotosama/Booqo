import { Injectable, inject, signal, computed } from '@angular/core';
import { AuthApiService } from './auth-api.service';
import { RegisterRequest, LoginRequest } from '../../models/auth.model';
import { tap } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

interface JwtPayload {
  role: string; // El rol (Role)
  sub: string; // El email (Subject)
  iat: number; // Fecha de emisión
  exp: number; // Fecha de expiración
}
@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly authApi = inject(AuthApiService);

  // --- Estado Privado (Encapsulamiento) ---
  private readonly _token = signal<string | null>(localStorage.getItem('token'));
  readonly userEmail = computed(() => {
    const token = this.token();
    if (!token) return null;
    try {
      const decoded = jwtDecode<JwtPayload>(token);
      return decoded.sub; // Aquí está el email que guardamos en el Back
    } catch {
      return null;
    }
  });

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
