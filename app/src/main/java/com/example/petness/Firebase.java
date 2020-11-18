package com.example.petness;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

public class Firebase extends AppCompatActivity {

    private static final String TAG = "FireBase";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

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
* */
}