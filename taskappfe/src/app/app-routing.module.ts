import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostTaskComponent } from './components/post-task/post-task.component';
import { GetAllTasksComponent } from './components/get-all-tasks/get-all-tasks.component';
import { UpdateTaskComponent } from './components/update-task/update-task.component';
import { ViewAllTasksComponent } from './components/view-all-tasks/view-all-tasks.component';

const routes: Routes = [
  {path:'insert',component:PostTaskComponent},
  {path:"",component:GetAllTasksComponent},
  {path:"update/:id",component:UpdateTaskComponent},
  {path:"view",component:ViewAllTasksComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
