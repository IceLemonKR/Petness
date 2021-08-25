package com.example.petness;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frag3 extends Fragment implements View.OnClickListener {

    private View view;
    private Context context;
    String formatDate;
    TextView dateNow;
    Button btnConnect;
    Button del;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    google_map.DBHelper dbHelper;

    final ArrayList<String> items = new ArrayList<String>();
    ArrayAdapter adapter;
    // listview 생성 및 adapter 지정.
    ListView listview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag3, container, false);
        context = container.getContext();
        Button del = (Button) view.findViewById(R.id.del);
        del.setOnClickListener(this);
        dateNow = (TextView) view.findViewById(R.id.dateNow);

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_multiple_choice, items);
        listview = (ListView) view.findViewById(R.id.listview1);
        listCreate();
        listview.setAdapter(adapter);
        return view;
    }

    public void listCreate(){
        adapter.clear();

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

        SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
        int count = adapter.getCount() ;

        for (int i = count-1; i >= 0; i--) {
            if (checkedItems.get(i)) {
                final int finalI = i;
                //Log.d(TAG, String.valueOf(finalI));
                db.collection("Petness")
                        .document(items.get(i).split("\n")[0])
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                //Log.d(TAG, items.get(finalI).split("\n")[0]);
                                items.remove(finalI);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //  Log.d(TAG, "DocumentSnapshot unsuccessfully deleted!");
                            }
                        });
            }
        }

        // 모든 선택 상태 초기화.
        listview.clearChoices() ;

    }

    @Override
    public void onClick(View view) {

        listDelete();
    }
}