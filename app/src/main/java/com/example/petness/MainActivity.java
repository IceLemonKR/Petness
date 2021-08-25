package com.example.petness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.petness.Frag1;
import com.example.petness.Frag2;
import com.example.petness.Frag3;
import com.example.petness.Frag4;
import com.example.petness.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;


    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_heart:
                        setFrag(0);
                        break;

                    case R.id.action_date:
                        setFrag(1);

                        break;

                    case R.id.action_map:
                        setFrag(2);
                        break;

                    case R.id.action_call:
                        setFrag(3);
                        break;
                }

                return true;
            }
        });
        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();
        frag4 = new Frag4();
        setFrag(0); // 첫 프레그먼트 화면을 무엇을 지정해 줄것인지 선택


    }

    //프레그 먼트 교체가 일어나는 실행문
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); // 교체 행위?
        switch (n){
            case 0:
                ft.replace(R.id.main_frame,frag1);
                ft.commit(); //저장
                break;

            case 1:
                ft.replace(R.id.main_frame,frag2);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.main_frame,frag3);
                ft.commit(); //저장
                break;

            case 3:
                ft.replace(R.id.main_frame,frag4);
                ft.commit(); //저장
                break;
        }
    }


}
