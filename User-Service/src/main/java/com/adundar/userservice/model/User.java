package com.adundar.userservice.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.adundar.userservice.utils.Utils;

@Document
public class User {

    @Id
    private String id;
    @NotNull
    @Size(min = 1, max = 36, message = "'name' attribute must be between {min} and {max} characters long")
    @Pattern(regexp = Utils.ALPHANUMERIC_UNDERSCORE_REGEX, message = "User name should consist only alphanumeric and underscores characters!")
    private String name;
    @NotNull
    private String lastName;
    private int    age;

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
