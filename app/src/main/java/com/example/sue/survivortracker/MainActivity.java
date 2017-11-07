package com.example.sue.survivortracker;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Person user;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ListView object from xml
        final ListView listView = (ListView) findViewById(R.id.listView);

        // Create a new Adapter
        final ArrayList<String> list= new ArrayList();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                list);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // Connect to the Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the todoItems child items it the database
        final DatabaseReference myRef = database.getReference("todoItems").child("users");
        final ArrayList<DataSnapshot> array = new ArrayList<>();
        ///////
//        com.google.firebase.database.Query query = myRef.child("Users").orderByChild("title").equalTo(name);
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Map<String,Object> map = (Map<String, Object>) dataSnapshot.getValue();
//                //String Title = (String) map.get("title");
//                String Title = dataSnapshot.child("title").getValue().toString();
//                mValueView.setText(Title);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        ////////
        // Assign a listener to detect changes to the child items
        // of the database reference.
        myRef.addChildEventListener(new ChildEventListener(){

            // This function is called once for each child that exists
            // when the listener is added. Then it is called
            // each time a new child is added.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
//                //String value = dataSnapshot.getValue(String.class);
//                value = dataSnapshot.getValue();
//                adapter.add(value.getFirstName());
                final Person value  = dataSnapshot.getValue(Person.class);
                adapter.add(value.toString());
                System.out.println("TEST: " + value.toString());

                //Log.i(adapter);
            }

            // This function is called each time a child item is removed.
            public void onChildRemoved(DataSnapshot dataSnapshot){
//                String value = dataSnapshot.getValue(String.class);
//                adapter.remove(value);
            }

            // The following functions are also required in ChildEventListener implementations.
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName){}
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName){}

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG:", "Failed to read value.", error.toException());
            }

        });

        // Add items via the Button and EditText at the bottom of the window.
        final EditText forename = (EditText) findViewById(R.id.firstName);
        final EditText surname = (EditText) findViewById(R.id.lastName);
        final EditText text = (EditText) findViewById(R.id.loc);
        final Button button = (Button) findViewById(R.id.addButton);
        final Button search = (Button) findViewById(R.id.search);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Create a new child with a auto-generated ID.
                //DatabaseReference childRef = myRef.push();
                //DatabaseReference usersRef = myRef.child("users");
                DatabaseReference usersRef = myRef;

                Map<String, Person> users = new HashMap<String, Person>();

                // Set the child's data to the value passed in from the text box.
                String first_name =  forename.getText().toString();
                String last_name =  surname.getText().toString();
                String position =  text.getText().toString();
                usersRef.child(first_name+" "+last_name).setValue(new Person(first_name, last_name,position));
                //childRef.setValue(text.getText().toString());
                //Person user = new Person(text.getText().toString())

                forename.setText("");
                surname.setText("");
                text.setText("");

            }



        });
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainSearch.class);
                startActivity(i);
                setContentView(R.layout.search_main);
            }
        });
       }
}

//____________________________________________________________________________
//          THIS is the Place that change layout
//        ************************************************************************************************
//        search.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                setContentView(R.layout.search_main);
//            }
//        });
//        ************************************************************************************************

