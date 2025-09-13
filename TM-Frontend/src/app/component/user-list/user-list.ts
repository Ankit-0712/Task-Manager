import { Component, OnInit, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { UserService } from '../../services/user';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
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

  deleteUser(userId: number): void {
    if (!confirm('Are you sure you want to delete this user?')) return;

    this.userService.deleteUser(userId).subscribe({
      next: () => {
        alert('User deleted successfully!');
        this.loadUsers(); // refresh the list
      },
      error: (err) => {
        console.error('Error deleting user', err);
        alert('Failed to delete user.');
      }
    });
  }
}
