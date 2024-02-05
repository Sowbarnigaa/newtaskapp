package com.example.MyApp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


//
//import com.example.MyApp.controller.TaskController;
//import com.example.MyApp.entity.Subtask;
//import com.example.MyApp.entity.Task;
//import com.example.MyApp.repo.TaskRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
//
//import java.util.Arrays;
//import java.util.Date;
//
//import static java.util.Arrays.asList;
//
//@Configuration
//public class ElasticsearchConfig {
//
//    @Autowired
//    private ElasticsearchOperations elasticsearchOperations;
//
//    @Bean
//    public void createTasksIndex() {
//        IndexCoordinates indexCoordinates = IndexCoordinates.of("tasks");
//
//        // Check if the index already exists before creating
//        if (!elasticsearchOperations.indexOps(indexCoordinates).exists()) {
//            elasticsearchOperations.indexOps(indexCoordinates).create();
//            // You can set additional settings and mappings here if needed
//            Task article = new Task();
//            article.setName("hi");
//            article.setDescription("helloooo");
//            article.setStatus("competed");
//            article.setDeadline(new Date("2024-02-03T11:36:00.000+00:00"));
//            Subtask subtask1 = new Subtask();
//            subtask1.setName("Subtask 1");
//            subtask1.setDescription("Description for Subtask 1");
//            subtask1.setStatus("In Progress");
//            article.setSubtasks(Arrays.asList(subtask1));
//            TaskRepo taskRepo = new TaskRepo();
//            TaskController taskController = new TaskController(taskRepo);
//            taskController.save(someTask);
//        }
//    }
//}

@EnableElasticsearchRepositories
@Configuration
public class ElasticsearchConfig extends ElasticsearchConfiguration {
    @Value("${ELASTICSEARCH_HOST:localhost}")
    private String host;
    @Value("${ELASTICSEARCH_PORT:9200}")
    private String port;
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(host+":"+port)
                .build();
    }
}