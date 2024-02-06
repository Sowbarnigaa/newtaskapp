package com.example.MyApp.controller;


public class TaskExistsException extends RuntimeException {
    public TaskExistsException(String id) {

        super("Task with id " + id + " already exists");
    }

    @Override
    public String getMessage() {

        return super.getMessage();
    }
}