package com.cycle_saver.graphQLUtilities;

import com.cycle_saver.dataFetchers.AllUsersDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.cycle_saver.dataFetchers.JourneysDataFetcher;
import com.cycle_saver.dataFetchers.UserDataFetcher;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Component
public class GraphQlUtility {

    @Value("classpath:schemas.graphqls")
    private Resource schemaResource;
    private GraphQL graphQL;
    private UserDataFetcher userDataFetcher;
    private JourneysDataFetcher journeysDataFetcher;
    private AllUsersDataFetcher allUsersDataFetcher;

    @Autowired
    GraphQlUtility(UserDataFetcher userDataFetcher,
                   JourneysDataFetcher journeysDataFetcher) throws IOException {
        this.userDataFetcher = userDataFetcher;
        this.journeysDataFetcher = journeysDataFetcher;
    }

    @PostConstruct
    public GraphQL createGraphQlObject() throws IOException {
        File schemas = schemaResource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        return  newGraphQL(schema).build();
    }

    public RuntimeWiring buildRuntimeWiring(){
        return newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("users", allUsersDataFetcher)
                        .dataFetcher("user", userDataFetcher))
                .type("User", typeWiring -> typeWiring
                        .dataFetcher("journeys", journeysDataFetcher))
                .build();
    }
}
