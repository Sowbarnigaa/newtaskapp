package com.example.MyApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Data
@Document(indexName = "tasks")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task{
   
    @Id
    @Field(type = FieldType.Text)
    private String id;
    @Field(type= FieldType.Text)
    private String name;
    @Field(type= FieldType.Text)
    private String description;
    @Field(type= FieldType.Text)
    private String status;
    @Field(type= FieldType.Date)
    private Date deadline;
    @Field(type = FieldType.Nested)
    private List<Subtask> subtasks;

     public Task(String id,String name, String description, String status, Date deadline,List<Subtask> subtasks ) {
         super();
         this.id=id;
         this.name=name;
         this.description=description;
         this.status=status;
         this.deadline=deadline;
         this.subtasks=subtasks;
     }

}