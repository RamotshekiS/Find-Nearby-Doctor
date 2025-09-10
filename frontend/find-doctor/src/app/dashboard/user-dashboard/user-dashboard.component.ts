import { Component } from '@angular/core';
import { UserDto } from '../../models/user';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-dashboard',
  standalone: false,
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css'
})
export class UserDashboardComponent {

  user: UserDto | null = null;
  loading = true;
  error: string | null = null;

  specialties = [
    { name: 'Cardiology', count: 12, icon: '❤️' },
    { name: 'Pediatrics', count: 8, icon: '👶' },
    { name: 'Dermatology', count: 6, icon: '💊' }
  ];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe({
      next: (data) => {
        this.user = data;
        this.loading = false;
      },
      error: (error) => {
        this.error = error.message || 'Failed to load user data';
        this.loading = false;
      }
    });
  }

}
