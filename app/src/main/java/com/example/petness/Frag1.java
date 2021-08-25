package com.example.petness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;


public class Frag1 extends Fragment {
    public static final String NOTIFICATION_CHANNEL_ID = "100";
    private View view;
    private BluetoothSPP bt;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1, container, false);
        context = container.getContext();
        bt = new BluetoothSPP(this.getActivity()); //Initializing
        final ArrayList<String> items = new ArrayList<String>();

        if (!bt.isBluetoothAvailable()) { //블루투스 사용 불가
            Toast.makeText(context
                    , "Bluetooth is not available"
                    , Toast.LENGTH_SHORT).show();

        }

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() { //데이터 수신

            public void onDataReceived(byte[] data, final String message) {

                final TextView text = (TextView)view.findViewById(R.id.dateNow); //XML파일에 있는 textview의 변수를 선언한다. 변수이름은 text
                final TextView suchi = (TextView)view.findViewById(R.id.suchi); //XML파일에 있는 textview의 변수를 선언한다. 변수이름은 text

                text.setText("bpm : " +message); // settext는 text를바꾸는 함수
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                if(Integer.parseInt(message)>=0 && Integer.parseInt(message)<=50) {
                    suchi.setText("좋음");
                }
                else if(Integer.parseInt(message)>=51 && Integer.parseInt(message)<=60){
                    suchi.setText("평균이상");
                }
                else if(Integer.parseInt(message)>=61 && Integer.parseInt(message)<=70){
                    suchi.setText("평균");
                }
                else if(Integer.parseInt(message)>=71 && Integer.parseInt(message)<=81){
                    suchi.setText("평균이하");
                }else if(Integer.parseInt(message)>=82){
                    suchi.setText("나쁨");
                }
            }
        });

        Button btnConnect = view.findViewById(R.id.btnConnect); //연결시도
        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getActivity(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });
        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(context
                        , "Connected to " + name + "\n" + address
                        , Toast.LENGTH_SHORT).show();
            }

            public void onDeviceDisconnected() { //연결해제
                Toast.makeText(context
                        , "Connection lost", Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() { //연결실패
                Toast.makeText(context
                        , "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });
                return view;
    }



    public void onDestroy() {
        super.onDestroy();
        bt.stopService(); //블루투스 중지
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        }
        else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);

            } else {
                Toast.makeText(context

                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();

            }
        }
    }



}

