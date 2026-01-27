import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest, AuthResponse, LoginRequest } from '../models/auth.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

/**
 * Servicio que se encarga de la autenticación del usuario.
 *
 */
@Injectable({ providedIn: 'root' })
export class AuthApiService {
  private readonly http = inject(HttpClient);
  private readonly url = `${environment.apiUrl}/auth`;
  /**
   * Metodo que se encarga de registrar un nuevo usuario.
   * @param data datos del usuario a registrar
   * @returns Observable<AuthResponse> respuesta del servidor
   */
  register(data: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.url}/register`, data);
  }

  login(data: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.url}/login`, data);
  }
}
