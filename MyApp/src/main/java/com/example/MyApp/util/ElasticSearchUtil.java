package com.example.MyApp.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import lombok.val;

import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static Supplier<Query> supplierWithNameField(String fieldValue){
        Supplier<Query> supplier=()->Query.of(q->q.match(matchQueryWithNameField(fieldValue)));
        return supplier;
    }
    public static MatchQuery matchQueryWithNameField(String fieldValue)
    {
        val matchQuery=new MatchQuery.Builder();
        return matchQuery.field("description").query(fieldValue).build();
    }

}