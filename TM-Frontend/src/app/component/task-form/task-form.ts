import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TaskService } from '../../services/task';
import { UserService } from '../../services/user';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../../models/user.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './task-form.html',
  styleUrls: ['./task-form.scss']
})
export class TaskForm implements OnInit {
  taskForm!: FormGroup;
  users: User[] = [];
  taskId?: number;

  constructor(
    private fb: FormBuilder,
    private taskService: TaskService,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Form with assignedToId (just the user ID)
    this.taskForm = this.fb.group({
      title: ['', Validators.required],
      description: [''],
      status: ['Pending', Validators.required],
      assignedToId: [null, Validators.required]
    });

    this.loadUsers();

    const idParam = this.route.snapshot.params['id'];
    if (idParam) {
      this.taskId = +idParam;
      this.loadTask(this.taskId);
    }
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe(users => {
      this.users = users;
    });
  }

  loadTask(id: number): void {
    this.taskService.getTaskById(id).subscribe(task => {
      this.taskForm.patchValue({
        title: task.title,
        description: task.description,
        status: task.status,
        assignedToId: task.assignedTo?.id
      });
    });
  }

  onSubmit(): void {
    if (this.taskForm.invalid) return;

    const payload = this.taskForm.value; // contains title, description, status, assignedToId

    if (this.taskId) {
      this.taskService.updateTask(this.taskId, payload).subscribe(() => {
        this.router.navigate(['/tasks']);
      });
    } else {
      this.taskService.createTask(payload).subscribe(() => {
        this.router.navigate(['/tasks']);
      });
    }
  }
}
