// // import { Component } from '@angular/core';
// // import { TaskService } from '../../service/task.service';
// // import { HttpClient } from '@angular/common/http';
// // import { Router } from '@angular/router';

// // @Component({
// //   selector: 'app-get-all-tasks',
// //   templateUrl: './get-all-tasks.component.html',
// //   styleUrl: './get-all-tasks.component.css'
// // })
// // export class GetAllTasksComponent {
// //     data: any;
// //     constructor(private taskService:TaskService,private router:Router){
// //       this.data=[];
// //     }

// //     ngOnInit()
// //     {
// //       this.getAllTasks();
     
// //       console.log("tryyyy"+this.data); // Log the data
     
// //     }
// //     getAllTasks()
// //     {
// //       this.taskService.getAllTask().subscribe((res:any)=>{
// //         console.log(res);
// //         console.log(res.content[0].name);
// //         console.log(res.numberOfElements);
// //         for(let i=0;i<res.numberOfElements;i++)
// //         {
// //           this.data.push(res.content[i])
// //         }
        
// //       })
// //     }

// //     deleteTask(id:number)
// //     {
// //       this.taskService.deleteTask(id).subscribe((res:any)=>{
// //         console.log(res);
// //         this.getAllTasks();
// //         if(res.id!=null)
// //           {
// //             this.router.navigateByUrl("")
// //           }
// //       })

// //     }
// // }
// import { Component } from '@angular/core';
// import { TaskService } from '../../service/task.service';
// import { HttpClient } from '@angular/common/http';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-get-all-tasks',
//   templateUrl: './get-all-tasks.component.html',
//   styleUrl: './get-all-tasks.component.css'
// })
// export class GetAllTasksComponent {
//     data: any;
    
//     constructor(private taskService: TaskService, private router: Router) {
//       this.data = [];
//     }

//     ngOnInit() {
//       this.getAllTasks();
//     }

//     getAllTasks() {
//       this.taskService.getAllTask().subscribe((res: any) => {
//         console.log(res);
//         console.log(res.content[0].name);
//         console.log(res.numberOfElements);

//         // Clear existing data before adding new data
//         this.data = [];

//         if (res.numberOfElements > 0) {
//           for (let i = 0; i < res.numberOfElements; i++) {
//             this.data.push(res.content[i]);
//           }
//         }
//       });
//     }

//     deleteTask(id: number) {
//       this.taskService.deleteTask(id).subscribe((res: any) => {
//         console.log(res);
//         this.getAllTasks();
//         if (res.id != null) {
//           this.router.navigateByUrl("");
//         }
//       });
//     }
// }
import { Component } from '@angular/core';
import { TaskService } from '../../service/task.service';
import { ActivatedRoute } from '@angular/router';


import { Router } from '@angular/router';

@Component({
  selector: 'app-get-all-tasks',
  templateUrl: './get-all-tasks.component.html',
  styleUrls: ['./get-all-tasks.component.css']
})
export class GetAllTasksComponent {
  data: any[];
  isAscendingSort: boolean = false;
  searchTerm: string;
  searchResults: any[];
  searchButtonPressed: boolean = false;
  showCompleted: boolean = true;
  showInProgress: boolean = true;
  showTodo: boolean = true;
  filteredResults: any[] = [];
  filteredData: any[] = [];
  errorOccurred: boolean = false;
  constructor(private taskService: TaskService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.data = [];
    this.searchResults = [];
    this.searchTerm="";
    this.showCompleted = true;
    this.showInProgress = true;
    this.showTodo = true;
    console.log("cons called");
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
        console.log(res.numberOfElements);
      }
      console.log(this.data);
      this.filteredData=this.data;
    },
    (error) => {
      console.error('Error fetching data:', error);
      this.errorOccurred = true;
      this.data=[];
      this.filteredData=[];
      this.filteredResults=[];
    }
    );
   
  }
  

  deleteTask(id: number) {
    this.taskService.deleteTask(id).subscribe((res: any) => {
      console.log(res);
      this.getAllTasks();
      if (res.id != null) {
        // Remove the deleted task from the local data array
        this.data = this.data.filter(task => task.id !== id);
  
        // If searching, update the search results
        if (this.searchButtonPressed) {
          this.searchResults = this.searchResults.filter(task => task.id !== id);
        }
      }
    });
  }


  // deleteTask(id: number) {
  //   this.taskService.deleteTask(id).subscribe((res: any) => {
  //     console.log(res);
  //     if (res.id != null) {
  //       // Remove the deleted task from the local data array
  //       this.data = this.data.filter(task => task.id !== id);
  
  //       // If searching, update the search results
  //       if (this.searchButtonPressed) {
  //         this.searchResults = this.searchResults.filter(task => task.id !== id);
  //       }
  //       this.cdr.detectChanges();
  //     }
  //   });
  // }
  

  toggleSort() {
    this.isAscendingSort = !this.isAscendingSort;
  
    if (this.searchButtonPressed) {
      // If searching, sort the search results
      this.sortTasks(this.filteredResults);
    } else {
      // Otherwise, sort the original data
      this.sortTasks(this.data);
    }
  }

  sortTasks(tasks: any[]) {
    tasks.sort((a, b) => {
      const dateA = new Date(a.deadline).getTime();
      const dateB = new Date(b.deadline).getTime();
  
      return this.isAscendingSort ? dateA - dateB : dateB - dateA;
    });
  }
  search() {
    if (this.searchTerm) {
      this.taskService.searchTask(this.searchTerm).subscribe(
        (results: any[]) => {
          console.log(results);
          this.searchButtonPressed = true;
          this.searchResults = results;
          console.log(this.searchResults,this.searchResults.length);
          // Sort the search results after retrieving them
          // this.sortTasks(this.searchResults);
          this.filteredResults=results;
          console.log(this.filteredResults);
        },
        (error: any) => {
          console.error('Error fetching search results:', error);
        }

      );
      
      
    }
    else{
      this.filteredResults=this.data;
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


}
