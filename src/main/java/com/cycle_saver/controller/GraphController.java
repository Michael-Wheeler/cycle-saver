package com.cycle_saver.controller;

import com.cycle_saver.graphQLUtilities.GraphQLUtility;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class GraphController {
    private GraphQL graphQL;
    private GraphQLUtility graphQlUtility;

    @Autowired
    GraphController(GraphQLUtility graphQlUtility) throws IOException {
        this.graphQlUtility = graphQlUtility;
        graphQL = graphQlUtility.createGraphQLObject();
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResponseEntity query(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        System.out.println("errors: "+result.getErrors());
        return ResponseEntity.ok(result.getData());
    }
}
