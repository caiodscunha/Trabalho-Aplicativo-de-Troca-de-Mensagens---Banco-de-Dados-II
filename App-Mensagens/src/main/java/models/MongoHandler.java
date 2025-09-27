package models;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;


public class MongoHandler {
    private static final String CONNECTION_STRING = Connection.CONNECTION_STRING.getValue();
    private static final String DATABASE_NAME = Connection.DATABASE_NAME.getValue();

    private static final MongoClient client = MongoClients.create(CONNECTION_STRING);

    public static MongoCollection<Document> getCollection(String name) {
        return client.getDatabase(DATABASE_NAME).getCollection(name);
    }

    public static List<Document> findDocuments(String collectionName, Document filter) {
        List<Document> results = new ArrayList<>();
        getCollection(collectionName).find(filter).into(results);
        return results;
    }

    public static void insertDocument(String collectionName, Document document) {
        getCollection(collectionName).insertOne(document);

    }

    public static void close() {
        client.close();
    }
}
