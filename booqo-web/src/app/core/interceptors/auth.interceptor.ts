import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
/**
 * Interceptor que añade el token al header de las peticiones
 * @param req Petición HTTP
 * @param next Siguiente interceptor
 * @returns Petición HTTP con el token
 */
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.token();

  // Si tenemos token, clonamos la petición y añadimos la cabecera
  if (token) {
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
    return next(cloned);
  }

  // Si no hay token, la petición sigue tal cual
  return next(req);
};
