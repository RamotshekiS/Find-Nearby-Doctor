import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiResponse, CreateUserRequest, JwtResponse, LoginRequest } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private apiUrl = 'http://localhost:8080/api/v1/users';
  private authUrl = 'http://localhost:8080/api/v1/auth';

constructor(private http: HttpClient) { }

  createUser(user: CreateUserRequest): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.apiUrl}/create`, user);
  }

  //Log user in using jwt
  login(credentials: LoginRequest): Observable<{ message: string, data: JwtResponse }> {
    return this.http.post<{ message: string, data: JwtResponse }>(`${this.authUrl}/login`, credentials);
  }

  //Log user out by removing token from local storage
 
  
}
