import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from '../../models/user';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginData: LoginRequest = {
    email: '',
    password: ''
  };

  message: string | null = null;
  success: boolean = false;
  loading = false;
  //router: any;


  constructor(private authService: AuthService, private router: Router) { }

  onSubmit(): void {
    this.loading = true;
    this.authService.login(this.loginData).subscribe({
      next: (response) => {
        console.log("Login response:", response);
        this.message = response.message;
        localStorage.setItem('token', response.data.token);
        localStorage.setItem('id', response.data.id.toString());
        localStorage.setItem('role', response.data.role);

        if (response.data.role === 'ADMIN') {
          this.router.navigate(['/admin/dashboard']);
        } else if (response.data.role === 'DOCTOR') {
          this.router.navigate(['/doctor/dashboard']);
        } else {
          this.router.navigate(['/user/dashboard']);
        }


        this.success = true;
        this.loading = false;
      },
      error: (error) => {
        this.message = error.message || 'Invalid credentials';
        this.success = false;
        this.loading = false;
      }
    });
  }

  
}
