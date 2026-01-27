export interface CenterRequest {
  name: string;
  cif: string;
  address: string;
  phone: string;
  email: string;
}

export interface CenterResponse {
  id: number;
  name: string;
  message: string;
}

export interface Center {
  id: number;
  name: string;
  cif: string;
  address: string;
  phone: string;
  email: string;
  employees: any[]; //TODO: Create Employee model
  clients: any[]; //TODO: Create Client model
}
