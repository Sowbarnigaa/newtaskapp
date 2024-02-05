package com.example.MyApp.repo;

import com.example.MyApp.entity.Task;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TaskRepo extends ElasticsearchRepository<Task,String> {
}