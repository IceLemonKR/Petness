package com.example.petness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Firebase extends AppCompatActivity {

    private static final String TAG = "FireBase";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Petness");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = database.getReference("message");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String location = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is : " + location);
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