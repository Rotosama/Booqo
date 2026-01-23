#  Booqo - Sistema de Gestión Clínica (MVP)

Este proyecto es una plataforma de gestión clínica que permite el registro y autenticación de usuarios. Se compone de un **Backend en Spring Boot 3** y un **Frontend en Angular 18**, conectados mediante una arquitectura de API REST segura.

## Estado Actual del Reto
El proyecto se encuentra en fase de **Producto Mínimo Viable (MVP)** con las siguientes funcionalidades operativas:
* Registro de nuevos usuarios con persistencia en Base de Datos.
* Autenticación (Login) con generación de tokens JWT.
* Sistema de notificaciones global basado en Signals.
* Persistencia del estado de sesión en LocalStorage.

---

##  Arquitectura del Backend (Spring Boot)

El servidor gestiona la seguridad y la lógica de negocio siguiendo el patrón de diseño por capas.

### **Seguridad y JWT**
* **JwtService**: Clase encargada de la generación, firma y validación de los tokens JSON Web Token.
* **JwtAuthenticationFilter**: Filtro personalizado que intercepta las peticiones para extraer y validar el token de la cabecera `Authorization`.
* **SecurityConfig**: Configuración central de Spring Security que establece la política `STATELESS` (sin estado) y define los permisos de acceso a los endpoints.

### **Estructura de Carpetas**
* `auth/controllers`: Endpoints de entrada para Registro y Login.
* `auth/services`: Lógica de validación de credenciales.
* `auth/repository`: Interfaz para la persistencia de usuarios (Spring Data JPA).

---

##  Arquitectura del Frontend (Angular 18)

Se ha utilizado una estructura modular enfocada en la reutilización de componentes y la reactividad moderna.

### **Gestión del Estado (Signals)**
En lugar de depender únicamente de Observables, se han implementado **Signals** para:
1. Almacenar el Token de autenticación de forma reactiva.
2. Gestionar el sistema de **Notificaciones Compartidas** (`NotificationService`), permitiendo lanzar avisos desde cualquier punto de la aplicación.

### **Comunicación con el API**
* **AuthApiService**: Manejo técnico de las peticiones HTTP.
* **AuthService**: Lógica de negocio en el cliente, gestionando el almacenamiento del token tras un registro exitoso (Autologin).

---

##  Tecnologías Utilizadas
* **Java 17 / Spring Boot 3** (Spring Security, JPA, Hibernate).
* **Angular 18** (Standalone Components, Signals, Reactive Forms).
* **JWT** (Json Web Token) para la seguridad.
* **CSS3** con diseño clínico profesional (Colores pastel y feedback visual).

---

##  Cómo arrancar el proyecto

1. **Backend**:
   ```bash
   mvn spring-boot:run
    ```
2. **Frontend**:
    ``` bash
    ng serve
    ```
   
## Acceder a la documentación API
Una vez arrancado el backend, puedes acceder a la documentación Swagger en:
```
http://localhost:8080/swagger-ui/index.html
```