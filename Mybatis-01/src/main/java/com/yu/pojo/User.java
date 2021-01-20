package com.yu.pojo;



//@Alias("stu") 如起别名直接使用的扫描整个包 可以用这个注解自定义别名
@SuppressWarnings("all")
public class User {
    private String name;
    private int age;
    private double heit;

    public User(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.heit = height;
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

    public double getHeight() {
        return heit;
    }

    public void setHeight(double height) {
        this.heit = height;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", heit=" + heit +
                '}';
    }
}
