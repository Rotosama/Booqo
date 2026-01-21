import { Injectable, signal } from '@angular/core';

export type NotificationType = 'success' | 'error' | 'info';

@Injectable({ providedIn: 'root' })
export class NotificationService {
  // Signal que guarda el mensaje y el tipo
  message = signal<string | null>(null);
  type = signal<NotificationType>('success');

  show(msg: string, type: NotificationType = 'success') {
    this.message.set(msg);
    this.type.set(type);

    // Auto-cerrar después de 3 segundos
    setTimeout(() => {
      this.message.set(null);
    }, 3000);
  }
}
