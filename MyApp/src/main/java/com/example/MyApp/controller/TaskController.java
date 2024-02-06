package com.example.MyApp.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.MyApp.entity.Task;
import com.example.MyApp.service.ElasticSearchService;
import com.example.MyApp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
@Controller
public class TaskController {


    @Autowired
    private final TaskService  taskService;

    @Autowired
    private final ElasticSearchService elasticSearchService;

    @GetMapping("/hello")
    public String disp(){
        return "Hiiiiii";
    }


    @GetMapping("/findAll")
    public Iterable<Task> findAll(){
        return taskService.getTasks();
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
    public ResponseEntity<Task> updateProduct(@PathVariable String id,@RequestBody  Task task){
        Task task_exist=taskService.getTaskById(id);
        if(task_exist==null)
        {
            return ResponseEntity.notFound().build();
        }
        System.out.println("****************");
        System.out.println("Data type of id field: " + task_exist.getId().getClass().getName());
        System.out.println("****************");
        task_exist.setName(task.getName());
        task_exist.setDescription(task.getDescription());
        task_exist.setStatus(task.getStatus());
        task_exist.setDeadline(task.getDeadline());
        task_exist.setSubtasks(task.getSubtasks());
        Task task_update=taskService.updateTask(task_exist);
        return ResponseEntity.ok(task_update);
    }

    @DeleteMapping("/update/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Task task_exist=taskService.getTaskById(id);
        if(task_exist==null)
        {
            return ResponseEntity.notFound().build();
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
