package com.example.sue.survivortracker;

/**
 * Created by Sue on 10/14/2017.
 */

public class Person {
    private String firstName;
    private String lastName;
    private String location;

    public Person(){
        firstName = "null";
        lastName = "null";
        location = "null";

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

<<<<<<< HEAD
    public String getName() {
        String fullname = this.firstName + " " + this.lastName;
        return fullname;
=======
    @Override
    public String toString () {
        return (this.firstName + " " + this.lastName + " " + this.location);
>>>>>>> faf95afaa591c76c87f837599a915cfd9921bcc8
    }

    public String getStatus() {
        String fullname = this.firstName + " " + this.lastName + " " + this.location;
        return fullname;
    }
}
