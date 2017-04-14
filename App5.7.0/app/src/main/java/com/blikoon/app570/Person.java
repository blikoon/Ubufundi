package com.blikoon.app570;

import java.util.UUID;

public class Person {

    private UUID UUID ;
    private String NAME ;
    private String AGE ;
    private String LOCATION ;

    public Person( String name, String age ,String location)
    {
        this.UUID = UUID.randomUUID();
        this.NAME = name;
        this.AGE = age;
        this.LOCATION = location;
    }

    public Person( String name, String age ,String location,UUID uuid)
    {
        this.UUID = uuid;
        this.NAME = name;
        this.AGE = age;
        this.LOCATION = location;
    }

    public UUID getUUID() {
        return UUID;
    }

    public void setUUID(UUID uuid) {
        this.UUID = uuid;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    @Override
    public String toString() {
        return "Name :"+NAME + " Age :"+ AGE + "Location :"+ LOCATION + " uuid :"+ UUID.toString();
    }
}
