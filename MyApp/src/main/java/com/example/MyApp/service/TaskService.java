package com.example.MyApp.service;

import com.example.MyApp.entity.Subtask;
import com.example.MyApp.entity.Task;
import com.example.MyApp.repo.TaskRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;




import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;


@Service
public class TaskService {


    private TaskRepo  taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;

    }

//    public Iterable<Task> getTasks() {
//        return taskRepo.findAll();
//    }
    public Page<Task> getTasks(Pageable pageable) {
        return taskRepo.findAll(pageable);
    }


//     public Page<Task> getTasks(int page, int size) {
//         PageRequest pageRequest = PageRequest.of(page, size);
//         return taskRepo.findAll(pageRequest);
//     }

    public Task getTaskById(String id) {
        return taskRepo.findById(id).orElse(null);
    }

    public Task insertTask(Task task) {
        return taskRepo.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepo.save(task);
    }

    public void deleteTask(String id ) {
        taskRepo.deleteById(id);
    }


}



