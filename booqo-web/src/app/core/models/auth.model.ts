export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface JwtPayload {
  role: string;
  hasCenter: boolean;
  sub: string;
  iat: number;
  exp: number;
}
