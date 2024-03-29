import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const BASIC_URL=["http://localhost:8080"]
@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http:HttpClient) { }

  postTask( task:any):Observable<any>
  {
    return this.http.post(BASIC_URL+"/tasks/insert",task);
  }
  getAllTask(page:number):Observable<any>
  {
    return this.http.get(BASIC_URL+"/tasks/findAll?page="+page);
  }
  getTaskById(id:number):Observable<any>
  {
    return this.http.get(BASIC_URL+"/tasks/update/"+id)
  }

  updateTask(id:number,task:any):Observable<any>
  {
    return this.http.put(BASIC_URL+"/tasks/update/"+id,task)
  }
  deleteTask(id:number):Observable<any>
  {
    return this.http.delete(BASIC_URL+"/tasks/update/"+id)
  }
  searchTask(query: string): Observable<any> 
  {
    return this.http.get(BASIC_URL+"/tasks/search/"+query)
  }
//
}
