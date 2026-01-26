import { Injectable, signal } from '@angular/core';

export type NotificationType = 'success' | 'error' | 'info';
/**
 * Servicio que se encarga de mostrar notificaciones.
 */
@Injectable({ providedIn: 'root' })
export class NotificationService {
  message = signal<string | null>(null);
  type = signal<NotificationType>('success');
  /** Metodo que se encarga de mostrar una notificacion
   * @param msg mensaje de la notificacion
   * @param type tipo de notificacion
   * @returns void
   */
  show(msg: string, type: NotificationType = 'success') {
    this.message.set(msg);
    this.type.set(type);

    setTimeout(() => {
      this.message.set(null);
    }, 3000);
  }
}
