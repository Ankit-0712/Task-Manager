import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user-form.html',
  styleUrls: ['./user-form.scss']
})
export class UserForm implements OnInit {
  user: any = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    timezone: '',
    isActive: true
  };
  isEditMode = false;

  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.userService.getUserById(+id).subscribe(data => {
        this.user = data;
      });
    }
  }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      alert('Please fill all required fields!');
      return;
    }

    if (this.isEditMode) {
      this.userService.updateUser(this.user.id, this.user).subscribe({
        next: () => this.router.navigate(['/users']),
        error: (err) => console.error(err)
      });
    } else {
      this.userService.createUser(this.user).subscribe({
        next: () => this.router.navigate(['/users']),
        error: (err) => console.error(err)
      });
    }
  }
}
