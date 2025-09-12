import { User } from './user.model';

export interface Task {
  id: number;
  title: string;
  description: string;
  status: string;
  createdAt: string;
  updatedAt: string;
  assignedTo: User; // now backend sends it
}
