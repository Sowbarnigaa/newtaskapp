package com.example.MyApp.controller;

public class RecordsNotFoundException extends RuntimeException {
    public RecordsNotFoundException(String noRecordsFound) {
        super(noRecordsFound);
    }
}
