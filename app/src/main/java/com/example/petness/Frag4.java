package com.example.petness;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag4 extends Fragment implements View.OnClickListener{

    private View view;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag4,container, false);
        Button button4 = (Button)view.findViewById(R.id.button4);
        context=container.getContext();
        button4.setOnClickListener(this);
        return  view;


    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.button4:
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01012344321"));

                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            }

        }
    }


}
