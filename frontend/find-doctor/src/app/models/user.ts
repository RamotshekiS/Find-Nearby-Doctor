export interface CreateUserRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}

export interface AppointmentDto {
  id: number;
  appointmentDate: string; 
  status: string;
  userId: number;
  specialty?: string;  
}

export interface UserDto {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  appointments?: AppointmentDto[];
}

export interface ApiResponse {
  message: string;
  data: UserDto | null;
}

export interface Role {
  id?: number;
  name: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface JwtResponse {
  id: number;
  token: string;
  role: string;
}