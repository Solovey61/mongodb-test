package net.sytes.solovey.mongodbTest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.jsr310.Jsr310CodecProvider;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

public class MongoConnector {
    private final String HOST;
    private final int PORT;

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection mongoCollection;

    public MongoConnector(String host, int port, String database, String collection, Class mongoObjectClass) {
        HOST = host;
        PORT = port;

        mongoClient = new MongoClient(HOST, PORT);
        mongoDatabase = mongoClient.getDatabase(database)
                .withCodecRegistry(CodecRegistries
                        .fromProviders(PojoCodecProvider.builder()
                                        .register(mongoObjectClass)
                                        .build(),
                                new Jsr310CodecProvider(),
                                new ValueCodecProvider()));
        mongoCollection = mongoDatabase.getCollection(collection, mongoObjectClass);
    }

    public void insertMany(Set set) {
        mongoCollection.insertMany(new ArrayList<>(set));
    }

    public LinkedHashSet getCollection() {
        return getCollection("{}");
    }

    public LinkedHashSet getCollection(String condition) {
        LinkedHashSet set = new LinkedHashSet();
        BsonDocument query = BsonDocument.parse(condition);
        mongoCollection.find(query).forEach((Consumer) set::add);
        return set;
    }

    public LinkedHashSet getSortedAndLimitedCollection(String sortingCondition, int limit) {
        LinkedHashSet set = new LinkedHashSet();
        BsonDocument query = BsonDocument.parse(sortingCondition);
        mongoCollection.find().sort(query).limit(limit).forEach((Consumer) set::add);
        return set;
    }

    public void clear() {
        mongoCollection.drop();
    }

    public void close() {
        mongoClient.close();
    }
}
