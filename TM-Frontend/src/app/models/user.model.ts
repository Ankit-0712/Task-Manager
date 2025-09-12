export interface User {
  id?: number;         // 👈 make it optional
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  timezone: string;
  isActive: boolean;
  tasks?: any[];        // 👈 you can also make tasks optional for creation
}
