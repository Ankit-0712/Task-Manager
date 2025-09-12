import { Component, OnInit, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { UserService } from '../../services/user';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'; // <-- add this

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [CommonModule, RouterModule], // <-- include RouterModule here
  templateUrl: './user-list.html',
  styleUrls: ['./user-list.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserList implements OnInit {
  users: any[] = [];

  constructor(private userService: UserService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe({
      next: (data) => {
        this.users = data;
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Error fetching users', err)
    });
  }
}
