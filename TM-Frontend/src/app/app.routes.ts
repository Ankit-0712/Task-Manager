import { Routes } from '@angular/router';
import { UserList } from './component/user-list/user-list';
import { UserForm } from './component/user-form/user-form';
import { TaskList } from './component/task-list/task-list';
import { TaskForm } from './component/task-form/task-form';

export const routes: Routes = [
    {path: '', redirectTo:'/users', pathMatch:'full'},

    //user Routes
    {path: 'users', component:UserList},
    {path: 'users/new', component:UserForm},
    {path: 'users/edit/:id', component:UserForm},

    //task Routes
    {path: 'tasks', component:TaskList},
    {path: 'tasks/new', component:TaskForm},
    {path: 'tasks/edit/:id', component:TaskForm},
];
