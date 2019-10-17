package com.haopn.config;

public class A {
    protected void help() {
        System.out.println("help from private");
    }
}

class B extends A {
    private A a;
    public B() {

    }
    public B(A a) {
        this.a = a;
    }
    @Override
    public void help() {
        a.help();
        System.out.println("help from public");
    }
}