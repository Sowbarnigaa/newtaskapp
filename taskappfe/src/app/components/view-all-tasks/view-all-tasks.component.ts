import { Component } from '@angular/core';
import { TaskService } from '../../service/task.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-view-all-tasks',

  templateUrl: './view-all-tasks.component.html',
  styleUrl: './view-all-tasks.component.css'
})
export class ViewAllTasksComponent {
  data: any[];
  isAscendingSort: boolean = false;
  searchTerm: string;
  searchResults: any[];
  searchButtonPressed: boolean = false;
  showCompleted: boolean = false;
  showInProgress: boolean = false;
  showTodo: boolean = false;
  filteredResults: any[] = [];
  filteredData: any[] = [];
tasks: any;
  constructor(private taskService: TaskService, private router: Router) {
    this.data = [];
    this.searchResults = [];
    this.searchTerm="";
}



  ngOnInit() {
    this.getAllTasks();
  }

  getAllTasks() {
    this.taskService.getAllTask().subscribe((res: any) => {
      console.log(res);
      console.log(res.content[0]?.name);
      console.log(res.numberOfElements);
  
      // Clear existing data before adding new data
      this.data = [];
  
      if (res.numberOfElements > 0) {
        for (let i = 0; i < res.numberOfElements; i++) {
          const task = res.content[i];
          const subtasks = task.subtasks || []; // Ensure subtasks array is present
  
          // Map subtasks to a new format if needed
          const formattedSubtasks = subtasks.map((subtask: any) => ({
            name: subtask.name,
            description: subtask.description,
           
            status: subtask.status,
          }));
  
          // Add the task with updated subtasks structure to the data array
          this.data.push({
            id: task.id,
            name: task.name,
            description: task.description,
            status: task.status,
            deadline: task.deadline,
            subtasks: formattedSubtasks,
          });
        }
      }
    });
  }
  

  deleteTask(id: number) {
    this.taskService.deleteTask(id).subscribe((res: any) => {
      console.log(res);
      this.getAllTasks();
      if (res.id != null) {
        this.router.navigateByUrl("");
      }
    });
  }

  toggleSort() {
    
    this.isAscendingSort = !this.isAscendingSort;
    this.sortTasks();
  }

  sortTasks() {
    this.data.sort((a, b) => {
      const dateA = new Date(a.deadline).getTime();
      const dateB = new Date(b.deadline).getTime();

      if (this.isAscendingSort) {
        return dateA - dateB;
      } else {
        return dateB - dateA;
      }
    });
  }
  search()
  {
    if (this.searchTerm) {
      this.taskService.searchTask(this.searchTerm).subscribe(
        (results: any[]) => {
          this.searchButtonPressed = true;
          this.searchResults = results;
        },
        (error: any) => {
          console.error('Error fetching search results:', error);
        }
      );
    }
  }

  filterByStatus() {
    this.filteredResults = this.searchResults.filter(task => {
      return (
        (this.showCompleted && task.status === 'completed') ||
        (this.showInProgress && task.status === 'in progress') ||
        (this.showTodo && task.status === 'todo')
      );
    });

    this.filteredData = this.data.filter(task => {
      return (
        (this.showCompleted && task.status === 'completed') ||
        (this.showInProgress && task.status === 'in progress') ||
        (this.showTodo && task.status === 'todo')
      );
    });
  }
  printAsPDF() {
    window.print();
  }

}
