package net.sytes.solovey.mongodbTest;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

public class MongoConnector {
    private final String HOST;
    private final int PORT;

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;

    public MongoConnector(String host, int port, String database, String collection) {
        HOST = host;
        PORT = port;

        mongoClient = new MongoClient(HOST, PORT);
        mongoDatabase = mongoClient.getDatabase(database);
        mongoCollection = mongoDatabase.getCollection(collection);
    }

    public void insertMany(Set set) {
//        set.forEach(o -> mongoCollection.insertOne());
    }

    public LinkedHashSet getCollection() {
        LinkedHashSet set = new LinkedHashSet();
        FindIterable<Document> documents = mongoCollection.find();
        documents.forEach((Consumer<Document>) set::add);
        return set;
    }

    public void clear() {
        mongoCollection.drop();
    }
}
