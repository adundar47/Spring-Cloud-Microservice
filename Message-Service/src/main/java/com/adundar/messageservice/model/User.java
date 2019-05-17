package com.adundar.messageservice.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -5470804563225486176L;

    private String            id;

    private String            name;

    private String            lastName;

    private int               age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", age=" + age + "]";
    }

}
