package com.example.petness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firebase extends AppCompatActivity {

    private static final String TAG = "FireBase";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]
    Intent intent= getIntent();

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootRef= firebaseDatabase.getReference();
    DatabaseReference dataRef = rootRef.child("location");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        //final DatabaseReference myRef = database.getReference("message");

        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // String location = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    @Override
    protected void onStart(){
        super.onStart();

        addChildEvent();
    }

    private void addChildEvent() {
    }

}

    /*
     {
 "Petness": {
   "location": {
      "longitude": 214.4654879,
      "latitude": 80.8967486
    }
  }
}

FirebaseDatabase database = FirebaseDatabase.getInstance()
DatabaseReference myRef = database.getReference("Petness")

status = myRef.child("location")
status.child("longitude").setValue(lon)
status.child("latitude").setValue(lat)
}
* */