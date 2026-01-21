import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest, AuthResponse, LoginRequest } from '../../models/auth.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthApiService {
  private readonly http = inject(HttpClient);
  private readonly url = 'http://localhost:8080/api/auth';

  register(data: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.url}/register`, data);
  }

  login(data: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.url}/login`, data);
  }
}
