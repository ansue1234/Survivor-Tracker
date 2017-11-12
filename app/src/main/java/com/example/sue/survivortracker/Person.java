package com.example.sue.survivortracker;

/**
 * Created by Sue on 10/14/2017.
 */

public class Person {
    private String firstName;
    private String lastName;
    private String location;
    private String uid;

    public Person(){
        firstName = "null";
        lastName = "null";
        location = "null";
        uid = "null";

    }
    public Person(String a, String b, String c, String d){
        firstName = a;
        lastName = b;
        location = c;
        uid = d;
    }


    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getLocation(){
        return location;
    }

    public String getUID() { return uid; }

    public String getName() {
        String fullname = this.firstName + " " + this.lastName;
        return fullname;
    }

    public String getStatus() {
        String fullname = this.firstName + " " + this.lastName + " " + this.location;
        return fullname;
    }
}
