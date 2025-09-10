import { Component } from '@angular/core';
import { CreateUserRequest } from '../../models/user';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-signup',
  standalone: false,
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  signupData: CreateUserRequest = {
    firstName: '',
    lastName: '',
    email: '',
    password: ''
  };

  message: string | null = null;
  success: boolean = false;

  showPassword: boolean = false;
  loading: boolean = false;

  constructor(private authService: AuthService) { }

  onSubmit() {
    this.loading = true;

    this.authService.createUser(this.signupData).subscribe({
      next: (response) => {
        this.message = response.message;
        this.success = true;
        //this.createUser.reset();
        this.loading = false;
      },
      error: (error) => {
        this.message = error.message || 'Something went wrong';
        this.success = false;
        this.loading = false;
      }
    });
  }

}

