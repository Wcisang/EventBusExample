package com.gwr.eventbusexample.eventbus;

import com.gwr.eventbusexample.domain.Person;

import java.util.List;

/**
 * Created by willi on 14/06/2016.
 */

public class MessageEB {
    private List<Person> list;
    private int number;
    private String text;
    private String classTester;

    public List<Person> getList() {
        return list;
    }
    public void setList(List<Person> list) {
        this.list = list;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getClassTester() {
        return classTester;
    }
    public void setClassTester(String classTester) {
        this.classTester = classTester;
    }
}

