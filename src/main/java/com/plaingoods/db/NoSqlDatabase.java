package com.plaingoods.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/** NoSQL database (local MongoDB, database "plaingoods") — admins. */
public class NoSqlDatabase {

    // serverSelectionTimeoutMS: fail after 3s (instead of 30s) if MongoDB isn't running
    private static final String URI = "mongodb://127.0.0.1:27017/?serverSelectionTimeoutMS=3000";

    private static MongoClient client;
    private static MongoDatabase database;

    public static void init() {
        client = MongoClients.create(URI);
        database = client.getDatabase("plaingoods");
    }

    public static MongoCollection<Document> admins() {
        return database.getCollection("admins");
    }

    public static void close() {
        if (client != null) {
            client.close();
        }
    }
}
