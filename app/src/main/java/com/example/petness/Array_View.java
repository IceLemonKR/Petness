package com.example.petness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Array_View extends AppCompatActivity {



    // 현재시간을 msec 으로 구한다.
    // 현재시간을 date 변수에 저장한다.

    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    //  SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String formatDate;
    TextView dateNow;
    Button btnConnect;
    Button del;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    google_map.DBHelper dbHelper;
    Intent a = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array__view);
        dateNow = (TextView) findViewById(R.id.dateNow);
        final ArrayList<String> items = new ArrayList<String>();
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items);
        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        listCreate();

        Button button = (Button)findViewById(R.id.del);
        button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                listDelete();
            }
        });
    }

    public void listCreate(){
        final ArrayList<String> items = new ArrayList<String>();
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items);
        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Petness")
                .orderBy("day", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(" z", null + "" + document.getData());
                                items.add(document.getId()+ "\n" + document.getData().get("Count"));
                               // Collections.reverse(items);
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.d("z", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void listDelete(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Petness")
        .document()
        .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}