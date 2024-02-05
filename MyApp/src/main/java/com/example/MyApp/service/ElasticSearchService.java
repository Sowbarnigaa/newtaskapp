package com.example.MyApp.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.MyApp.entity.Task;
import com.example.MyApp.util.ElasticSearchUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Supplier;
@Service
public class ElasticSearchService {
    private ElasticsearchClient elasticsearchClient;

    public ElasticSearchService(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public SearchResponse<Task> matchTaskWithName(String fieldValue) throws IOException{
        Supplier<Query> supplier= ElasticSearchUtil.supplierWithNameField(fieldValue);
        SearchResponse<Task> searchResponse=elasticsearchClient.search(s->s.index("tasks").query(supplier.get()),Task.class);
        System.out.println("query: "+supplier.get().toString());
        return searchResponse;

    }
}
