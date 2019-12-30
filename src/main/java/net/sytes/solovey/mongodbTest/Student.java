package net.sytes.solovey.mongodbTest;

import java.util.HashSet;

public class Student {
    private String name;
    private int age;
    private HashSet<String> courses;

    public Student() {
    }

    public Student(String name, int age, HashSet<String> courses) {
        this.name = name;
        this.age = age;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HashSet<String> getCourses() {
        return courses;
    }

    public void setCourses(HashSet<String> courses) {
        this.courses = courses;
    }
}
