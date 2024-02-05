import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostTaskComponent } from './components/post-task/post-task.component';
import { GetAllTasksComponent } from './components/get-all-tasks/get-all-tasks.component';
import { UpdateTaskComponent } from './components/update-task/update-task.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { ViewAllTasksComponent } from './components/view-all-tasks/view-all-tasks.component';

@NgModule({
  declarations: [
    AppComponent,
    PostTaskComponent,
    GetAllTasksComponent,
    UpdateTaskComponent,
    ViewAllTasksComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
