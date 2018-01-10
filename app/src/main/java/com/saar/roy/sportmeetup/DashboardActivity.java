package com.saar.roy.sportmeetup;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.saar.roy.sportmeetup.MainActivity.LOG_AUTH_TAG;

public class DashboardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private ArrayAdapter<String> arrayAdapter;
    private final String DB_TAG = "Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        ListView listView = (ListView) findViewById(R.id.friendList);
        listView.setAdapter(arrayAdapter);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> friendsUids = (List<String>)dataSnapshot.getValue();
                arrayAdapter.clear();
                arrayAdapter.addAll(friendsUids);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(DB_TAG, "loadPost:onCancelled", databaseError.toException());
            }
        } ;
        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).child("friends").addListenerForSingleValueEvent(eventListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accountButton:
                // Show user settings
                return true;

            case R.id.signOutButton:
                mAuth.signOut();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                // Add on finish listener
                //startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
