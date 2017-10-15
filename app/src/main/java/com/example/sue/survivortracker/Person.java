package com.example.sue.survivortracker;

/**
 * Created by Sue on 10/14/2017.
 */

public class Person {
    public String firstName;
    public String lastName;
    public String location;
    public Person(){
    }
    public Person(String a, String b, String c){
        firstName = a;
        lastName = b;
        location = c;
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

    public String getName () {
        String fullname = getFirstName() + " " + getLastName() + " " + getLocation();
        return fullname;
    }
}
