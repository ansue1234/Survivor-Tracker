package com.example.sue.survivortracker;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
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
        final DatabaseReference myRef = database.getReference("todoItems");

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
                adapter.add(value.getName());
                Log.i(adapter);
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


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Create a new child with a auto-generated ID.
                //DatabaseReference childRef = myRef.push();
                DatabaseReference usersRef = myRef.child("users");

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

        // Delete items when clicked
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // look for refrence in database
//                Query myQuery = myRef.orderByValue().equalTo((String)
//                        listView.getItemAtPosition(position));
//                // listen to the database
//                myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    //get a data snapshot of the database
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.hasChildren()) {
//                            //look for the key of the text and remove it
//                            DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
//
//                            // removes item in database
//                            firstChild.getRef().removeValue();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                })
//                ;}
//        })
       }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
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

