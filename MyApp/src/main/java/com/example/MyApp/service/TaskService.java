package com.example.MyApp.service;

import com.example.MyApp.entity.Task;
import com.example.MyApp.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;


import java.util.List;

@Service
public class TaskService {


    private TaskRepo  taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Iterable<Task> getTasks() {
        return taskRepo.findAll();
    }

    // public Page<Task> getTasks(int page, int size) {
    //     PageRequest pageRequest = PageRequest.of(page, size);
    //     return taskRepo.findAll(pageRequest);
    // }

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
