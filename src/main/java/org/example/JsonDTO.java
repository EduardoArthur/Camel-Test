package org.example;

public class JsonDTO {
    private int id;
    private String name;
    private int age;
    private boolean auth;

    public JsonDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean getAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "JsonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", auth='" + auth + '\'' +
                '}';
    }
}
