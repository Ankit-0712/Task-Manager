import { Component, OnInit, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { TaskService } from '../../services/task';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './task-list.html',
  styleUrls: ['./task-list.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class TaskList implements OnInit {
  tasks: any[] = [];

  constructor(private taskService: TaskService, private cdr: ChangeDetectorRef, private router: Router) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks(): void {
    this.taskService.getAllTasks().subscribe({
      next: (data) => {
        this.tasks = data;
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Error fetching tasks', err)
    });
  }

  editTask(taskId: number) {
    this.router.navigate(['/tasks/edit', taskId]);
  }

  deleteTask(taskId: number) {
    if (confirm('Are you sure you want to delete this task?')) {
      this.taskService.deleteTask(taskId).subscribe(() => {
        this.loadTasks(); // refresh list
      });
    }
  }
}
