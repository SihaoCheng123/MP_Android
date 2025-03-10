package com.example.mealplanner.model.data;

public class User_Data {
    private Long id;
    private String name;
    private int age;
    private String phone;

    public User_Data(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public User_Data(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User_Data{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}
