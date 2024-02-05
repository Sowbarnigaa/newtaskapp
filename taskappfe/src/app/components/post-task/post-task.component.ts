import { Component } from '@angular/core';
import { TaskService } from '../../service/task.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post-task',
  templateUrl: './post-task.component.html',
  styleUrl: './post-task.component.css'
})
export class PostTaskComponent {
  postTaskForm: any;
  
  task: any;
  subtasksFormArray: any;
  constructor(private taskService:TaskService, private fb:FormBuilder,
    private router: Router)
  {

  }
  ngOnInit()
  {
    this.postTaskForm=this.fb.group({
      name:[null,[Validators.required]],
      description:[null,[Validators.required]],
      status:[null,[Validators.required]],
      deadline:[null,[Validators.required]],
      subtasks: this.fb.array([]),
      
    })
    this.subtasksFormArray = this.postTaskForm.get('subtasks') as FormArray;
  }
  
  subtasks() : FormArray {  
    return this.postTaskForm.get("subtasks") as FormArray  
  }  
  
  newSubtask(): FormGroup {  
    return this.fb.group({  
      name: '',  
      description: '',  
      status:''
    })  
  }  

  postTask()
  {
    console.log(this.postTaskForm.value);
    this.taskService.postTask(this.postTaskForm.value).subscribe((res)=>
    {
      console.log(res);
      this.router.navigateByUrl("/");
    })
  }
  addSubtask() {
    this.subtasks().push(this.newSubtask());
       
  }

  removeSubtask(index: number) {
   this.subtasks().removeAt(index);
  }

}
