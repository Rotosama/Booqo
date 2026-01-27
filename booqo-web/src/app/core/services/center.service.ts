import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { CenterRequest, CenterResponse } from '@core/models/center.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CenterService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/centers`;

  createCenter(data: CenterRequest): Observable<CenterResponse> {
    return this.http.post<CenterResponse>(`${this.apiUrl}/create`, data);
  }

  getMyCenter(): Observable<CenterResponse> {
    return this.http.get<CenterResponse>(`${this.apiUrl}/my-center`);
  }
}
