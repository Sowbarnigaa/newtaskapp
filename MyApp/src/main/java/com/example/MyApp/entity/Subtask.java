package com.example.MyApp.entity;

// Subtask.java

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
@Data
@Document(indexName = "tasks")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subtask {


    private String name;
    private String description;

    private String status;

    // Getters and setters
}
