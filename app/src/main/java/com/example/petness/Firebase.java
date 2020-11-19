package com.example.petness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Firebase extends AppCompatActivity {

    private static final String TAG = "FireBase";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]
    Intent intent= getIntent();
    double latitude = intent.getDoubleExtra("latidute",0.0);
    double longitude = intent.getDoubleExtra("longitude", 0.0);
   // String longitude = intent.getStringExtra("longitude");

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
                String location = dataSnapshot.getValue(String.class);
             //  Log.d(TAG, "Value is : " + latitude);
              // Log.d(TAG, "Value is : " + longitude);
                rootRef.child("latitude").setValue(latitude);
                rootRef.child("longitude").setValue(longitude);
               // myRef.setValue(TAG, location);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

/*
    @IgnoreExtraProperties
    public class location {
        public String latitude;
        public String longitude;
        public HashMap<String, String> result = new HashMap<>();
        public location(){

        }
        public location(String latitude, String longitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }
        @Exclude
        public HashMap<String, String> toLocaion(){
            HashMap<String, String> result = new HashMap<>();
            result.put("latidute",latitude);
            result.put("longitude",longitude);

            return result;
        }*/


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