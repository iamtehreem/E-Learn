package com.i190405.task2;

public class enroll_model {

    String name,coursename,age;

    public enroll_model(String name, String coursename, String age) {
        this.name = name;
        this.coursename = coursename;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
