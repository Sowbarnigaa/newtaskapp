package com.example.MyApp.service;

import com.example.MyApp.entity.Task;
import com.example.MyApp.repo.TaskRepo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;



import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

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
//    private TransportClient transportClient;
//
//    public TaskService(@Value("${elasticsearch.host}") String host,
//                                @Value("${elasticsearch.port}") int port) throws UnknownHostException {
//        Settings settings = Settings.builder()
//                .put("cluster.name", "myClusterName") // Replace with your cluster name
//                .build();
//        transportClient = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
//    }
//
//    public SearchResponse executeSearchAndPaginate(int from, int size) {
//        // Build the search request
//        SearchRequest searchRequest = new SearchRequest("tasks");
//        searchRequest.source().query(QueryBuilders.matchAllQuery());
//        searchRequest.source().from(from);
//        searchRequest.source().size(size);
//
//        // Execute the search request and return the response
//        return transportClient.search(searchRequest).actionGet();
//    }
}



