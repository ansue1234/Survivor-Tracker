package com.example.sue.survivortracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import com.example.sue.survivortracker.R;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by cap1 on 10/15/17.
 */

public class MainSearch extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.search_main);

         //Get ListView object from xml
        final ListView listView = (ListView) findViewById(R.id.resultView);

        // Create a new Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        //array of persons
        final ArrayList<String> keys = new ArrayList<String>();

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // Connect to the Firebase database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the todoItems child items it the database
        final DatabaseReference myRef = database.getReference("data").child("users");

        final Button backButton = (Button) findViewById(R.id.back);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                setContentView(R.layout.activity_main);
            }
        });


        final EditText searchField = (EditText) findViewById(R.id.nameSearch);
        final Button searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String searchFieldText = searchField.getText().toString();
                myRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                        for (int i=0; i < keys.size(); i++) {
                            final Person person = dataSnapshot.getValue(Person.class);
                            String personName = person.getName();
                            System.out.println("Keys " + dataSnapshot.getKey());
                            if (dataSnapshot.getKey() == "Bob Smith ") {
                                System.out.println("Keys can find them");
                            }
                            if (personName.equals(searchFieldText)) {
                                System.out.println("We made it past the if statement");
                                adapter.add(person.getName());
                                System.out.println("Keys " + adapter.getCount());
                            }
//                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        // Assign a listener to detect changes to the child items
        // of the database reference.
        myRef.addChildEventListener(new ChildEventListener() {

            // This function is called once for each child that exists
            // when the listener is added. Then it is called
            // each time a new child is added.

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Person value = dataSnapshot.getValue(Person.class);
                //adapter.add(value.getName());
//                keys.add(dataSnapshot.getKey());
//                System.out.println("Keys " + keys);
//                personList.add(value.getName());
//                System.out.println(personList);
            }

            // This function is called each time a child item is removed.
            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                Person value = dataSnapshot.getValue(Person.class);
//                adapter.add(value.getName());
            }

            // The following functions are also required in ChildEventListener implementations.
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG:", "Failed to read value.", error.toException());
            }
        });
    }
}
