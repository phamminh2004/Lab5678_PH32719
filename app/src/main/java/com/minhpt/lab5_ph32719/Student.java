package com.minhpt.lab5_ph32719;

import java.io.Serializable;

public class Student implements Serializable {
    public String branch;

    public String name;
    public String address;


    public void setAddress(String address) {
        this.address = address;
    }

    public Student(String branch, String name, String address) {
        this.branch = branch;
        this.name = name;
        this.address = address;
    }
}
