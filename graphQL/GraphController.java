package com.cycle_saver.controller;

import com.cycle_saver.graphQLUtilities.GraphQlUtility;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@RestController
public class GraphController {
    private GraphQL graphQL;
    private GraphQlUtility graphQlUtility;

    @Autowired
    GraphController(GraphQlUtility graphQlUtility) throws IOException {
        this.graphQlUtility = graphQlUtility;
        graphQL = this.graphQlUtility.createGraphQlObject();
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @Produces(MediaType.APPLICATION_JSON)
    public Response query(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        System.out.println("errors: "+result.getErrors());
        return Response.ok(result.getData()).build();
    }
}
