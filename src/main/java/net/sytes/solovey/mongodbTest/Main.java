package net.sytes.solovey.mongodbTest;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentsParser parser = new StudentsParser("mongo.csv");
        MongoConnector connector = new MongoConnector("172.18.0.2", 27017, "test", "students");
        connector.clear();
        connector.insertMany(parser.getStudents());
        connector.getCollection().forEach(System.out::println);

    }
}
