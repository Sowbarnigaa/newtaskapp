// import { Component } from '@angular/core';
// import { ActivatedRoute } from '@angular/router';
// import { TaskService } from '../../service/task.service';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-update-task',
//   templateUrl: './update-task.component.html',
//   styleUrl: './update-task.component.css'
// })
// export class UpdateTaskComponent {
//   updateTaskForm: any;

//   id:number=this.activatedRoute.snapshot.params["id"];

//   constructor(private activatedRoute:ActivatedRoute,
//   private service:TaskService,
//   private fb:FormBuilder ,
//   private router:Router)
//   {

//   }
//   ngOnInit()
//   {
//     this.updateTaskForm=this.fb.group({
//       name:[null,[Validators.required]],
//       description:[null,[Validators.required]],
//       status:[null,[Validators.required]],
//       deadline:[null,[Validators.required]]
    
//     }),
      
//     this.getTaskById();
//     }

//   getTaskById(){
//     this.service.getTaskById(this.id).subscribe((res)=>{
//       console.log(res);
//       this.updateTaskForm.patchValue(res);
//     })
//   }

//   updateTask()
//   {
//     this.service.updateTask(this.id,this.updateTaskForm.value).subscribe((res)=>
//     {
//       console.log(res);
//       if(res.id!=null)
//       {
//         this.router.navigateByUrl("")
//       }

//     })

//   }

// }
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TaskService } from '../../service/task.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.css']
})
export class UpdateTaskComponent {

  updateTaskForm: any;
  id: number;
  subtasksFormArray: any;
  constructor(
    private activatedRoute: ActivatedRoute,
    private service: TaskService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.id = this.activatedRoute.snapshot.params["id"];
  }

  ngOnInit() {
    this.updateTaskForm = this.fb.group({
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
      status: [null, [Validators.required]],
      deadline: [null, [Validators.required]],
      subtasks: this.fb.array([]),
    });
    this.subtasksFormArray = this.updateTaskForm.get('subtasks') as FormArray;
    this.getTaskById();
  }
  subtasks() : FormArray {  
    return this.updateTaskForm.get("subtasks") as FormArray  
  }  
  
  newSubtask(): FormGroup {  
    return this.fb.group({  
      name: '',  
      description: '',  
     
      status:''
    })  
  }  
  getTaskById() {
    this.service.getTaskById(this.id).subscribe((res) => {
      console.log(res);

      // Parse the deadline string into a Date object
      const deadlineDate = new Date(res.deadline);

      // Format the date for the datetime-local input
      const formattedDeadline = deadlineDate.toISOString().slice(0, 16);
      res.subtasks.forEach((subtask: { name: any; description: any; status: any; deadline: any; }) => {
        this.subtasksFormArray.push(this.fb.group({
          name: [subtask.name],
          description: [subtask.description],
          status: [subtask.status],
         
        }));
      });
      // Patch the form with the formatted deadline
      this.updateTaskForm.patchValue({
        name: res.name,
        description: res.description,
        status: res.status,
        deadline: formattedDeadline,
        subtasks:res.subtasks
      });
    });
  }

  updateTask() {
    this.service.updateTask(this.id, this.updateTaskForm.value).subscribe((res) => {
      console.log(res);
      if (res.id != null) {
        this.router.navigateByUrl("");
      }
    });
  }

  addSubtask() {
    this.subtasks().push(this.newSubtask());
       
  }

  removeSubtask(index: number) {
   this.subtasks().removeAt(index);
  }

}
