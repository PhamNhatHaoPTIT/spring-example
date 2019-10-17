package com.haopn.config;

public class Main {
    public static void main(String[] args) {
        A a = new B(new A());
        a.help();
    }
}
