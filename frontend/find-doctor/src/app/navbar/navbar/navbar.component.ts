import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { UserDto } from '../../models/user';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {

  user: UserDto | null = null;

  constructor(private userService: UserService ) {}

  
  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe({
      next: (userData: UserDto) => {
        this.user = userData;
      },
      error: (error) => {
        console.error('Error fetching user:', error);
        this.user = null;
      }
    });

  }

}
