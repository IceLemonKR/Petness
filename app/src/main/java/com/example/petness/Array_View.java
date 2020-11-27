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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

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

        View.OnClickListener listener = new View.OnClickListener() {
                SQLiteDatabase db1;
                String sql;
            @Override
            public void onClick(View view) {
               /* long now = System.currentTimeMillis();
                Date date = new Date(now);
                //  formatDate = sdfNow.format(date);
                Collections.reverse(items);
                items.add( " / " + "동물발견!!!!!!!!!");
                //  items.add(formatDate + " / " + "동물발견!!!!!!!!!");
                Collections.reverse(items);
                // listview 갱신
                adapter.notifyDataSetChanged();*/
               switch (view.getId()){
                   case R.id.btnConnect:
                       selectData();

//                    Cursor cursor = db1.rawQuery(sql, null);
//                    if(cursor.getCount() > 0){
//                        while(cursor.moveToNext()){
//                            Collections.reverse(items);
//                            items.add(String.format("\n걸음수 : %s",cursor.getString(0)));
//                            Collections.reverse(items);
//                            adapter.notifyDataSetChanged();
//                        }
//                    }
               }
                dbHelper.close();
            }
        };
        Button deleteButton = (Button)findViewById(R.id.del);
        Button btnConnect = (Button) findViewById(R.id.btnConnect);
        Button del = (Button) findViewById(R.id.del);
        btnConnect.setOnClickListener(listener);
        del.setOnClickListener(listener);

        deleteButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount() ;

                for (int i = count-1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        items.remove(i) ;
                    }
                }

                // 모든 선택 상태 초기화.
                listview.clearChoices() ;

                adapter.notifyDataSetChanged();
            }
        }) ;
    }

    private void selectData(){
        SQLiteDatabase db1;
        String sql;
        db1 = dbHelper.getReadableDatabase();
        sql = "SELECT * FROM Petness;";
        Log.d("test : ",sql);

    }
//

    /*private void selectWhereDoc(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Petness").whereEqualTo("Count",cnt).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        UserInfo
                    }
                }
            }
        })
    }*/
}