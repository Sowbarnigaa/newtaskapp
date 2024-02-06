package com.example.MyApp.controller;


public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String id) {

        super("No task with an ID " + id +" exists");
    }
    @Override
    public String getMessage() {

        return super.getMessage();
    }
}