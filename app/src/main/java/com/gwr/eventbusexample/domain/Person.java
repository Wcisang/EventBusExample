package com.gwr.eventbusexample.domain;

/**
 * Created by willi on 14/06/2016.
 */
public class Person {

    private String name;
    private String job;

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
