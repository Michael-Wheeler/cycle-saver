//package com.cycle_saver.db;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoDatabase;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.codecs.pojo.PojoCodecProvider;
//import com.mongodb.MongoClientSettings;
//import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
//import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
//
//public class MongoDbConnection {
//
//    public MongoDatabase getDb(){
//        return getMongoClient().getDatabase("cycleSaver").withCodecRegistry(getCodecRegistry());
//    }
//
//    private MongoClient getMongoClient() {
//        return MongoClients.create();
//    }
//
//    private CodecRegistry getCodecRegistry(){
//        return fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
//                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
//    }
//}