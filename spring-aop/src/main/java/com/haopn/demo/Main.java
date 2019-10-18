package com.haopn.demo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");

        Student student = (Student) context.getBean("student");
        student.getName();
        student.getAge();
    }
}
