package com.example.MyApp.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.MyApp.entity.Task;
import com.example.MyApp.service.ElasticSearchService;
import com.example.MyApp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
@CrossOrigin("*")
@Controller
public class TaskController {


    @Autowired
    private final TaskService  taskService;

    @Autowired
    private ElasticSearchService elasticSearchService;



//    @GetMapping("/findAll")
//    public ResponseEntity<Iterable<Task>> findAll(){
//        Iterable<Task> tasks = taskService.getTasks();
//        return ResponseEntity.ok().body(tasks);
//    }
    @GetMapping("/findAll")
    public ResponseEntity<Page<Task>> findAll(@RequestParam(defaultValue = "0") int page) {
        PageRequest pageable = PageRequest.of(page, 2, Sort.by("id").descending());
        Page<Task> tasks = taskService.getTasks(pageable);
        return ResponseEntity.ok().body(tasks);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Log the exception
        ex.printStackTrace();
        // Return an appropriate response to the client
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Task doesn't exist");
    }
    @GetMapping("/update/{id}")
    public ResponseEntity<Task> find(@PathVariable String id){
        Task task=taskService.getTaskById(id);
        if(task==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/insert")
    public Task insertProduct(@RequestBody  Task task){
        return taskService.insertTask(task);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateProduct(@PathVariable String id, @RequestBody Task task) {
        Task task_exist = taskService.getTaskById(id);


        // Update the existing task with the new task details
        task_exist.setName(task.getName());
        task_exist.setDescription(task.getDescription());
        task_exist.setStatus(task.getStatus());
        task_exist.setDeadline(task.getDeadline());
        task_exist.setSubtasks(task.getSubtasks());

        // Update the task in the service
        Task updatedTask = taskService.updateTask(task_exist);

        // Return the updated task with 200 OK status
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/update/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Task task_exist = taskService.getTaskById(id);
        if (task_exist == null) {
            // Return a custom message with 404 status code
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No task found with ID: " + id);
        }
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/match/{fieldValue}")
    public List<Task> matchAllTaskWithName(@PathVariable String fieldValue) throws IOException {
        SearchResponse<Task> searchResponse =  elasticSearchService.matchTaskWithName(fieldValue);
        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Task>> listOfHits= searchResponse.hits().hits();
        List<Task> listOfTasks  = new ArrayList<>();
        for(Hit<Task> hit : listOfHits){
            listOfTasks.add(hit.source());
        }
        return listOfTasks;
    }

}
