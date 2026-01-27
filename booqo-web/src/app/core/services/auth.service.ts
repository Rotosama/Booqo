import { Injectable, inject, signal, computed } from '@angular/core';
import { AuthApiService } from './auth-api.service';
import { RegisterRequest, LoginRequest, AuthResponse, JwtPayload } from '../models/auth.model';
import { Observable, tap } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly authApi = inject(AuthApiService);

  private readonly _token = signal<string | null>(localStorage.getItem('token'));

  readonly userEmail = computed(() => {
    const token = this.token();
    if (!token) return null;
    try {
      const decoded = jwtDecode<JwtPayload>(token);
      return decoded.sub;
    } catch {
      return null;
    }
  });

  readonly hasCenter = computed(() => {
    const token = this.token();
    if (!token) return false;
    try {
      const decoded = jwtDecode<any>(token);
      return decoded.hasCenter === true;
    } catch {
      return false;
    }
  });

  readonly token = this._token.asReadonly();
  readonly isAuthenticated = computed(() => !!this._token());

  register(data: RegisterRequest): Observable<AuthResponse> {
    return this.authApi.register(data).pipe(
      tap((res) => {
        localStorage.setItem('token', res.token);
        this._token.set(res.token);
      }),
    );
  }

  login(data: LoginRequest): Observable<AuthResponse> {
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
