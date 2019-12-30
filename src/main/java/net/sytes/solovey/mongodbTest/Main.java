package net.sytes.solovey.mongodbTest;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentsParser parser = new StudentsParser("mongo.csv");
        MongoConnector connector = new MongoConnector("172.18.0.2", 27017, "test", "students", Student.class);
        connector.clear();
        connector.insertMany(parser.getStudents());


        System.out.println("Общее количество студентов: " + connector.getCollection().size());
        System.out.println("Студентов старше 40 лет: " + connector.getCollection("{age: {$gt: 40}}").size());

        Student youngestStudent = (Student) connector.getSortedAndLimitedCollection("{age: 1}", 1).toArray()[0];
        System.out.println("Самый молодой студент: " + youngestStudent.getName());

        Student oldestStudent = (Student) connector.getSortedAndLimitedCollection("{age: -1}", 1).toArray()[0];
        System.out.println("Курсы самого старого студента: " + oldestStudent.getCourses());
        connector.close();
    }
}
