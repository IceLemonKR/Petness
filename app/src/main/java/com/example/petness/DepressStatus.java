package com.example.petness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DepressStatus {
    public static final String UNKNOWN = "0";
    public static final String BAD = "1";

    public static int getDepressDrawObj(int depressStatus) {
        int result = getDepressDrawObj(String.valueOf(depressStatus));
        return result;
    }

    public static int getDepressDrawObj(String depressStatus) {
        int drawable;
        switch (depressStatus) {
            case DepressStatus.BAD:
                drawable = R.drawable.bs_checkbox_feeling_bad;
                break;
            case DepressStatus.SAD:
                drawable = R.drawable.bs_checkbox_feeling_sad;
                break;
            case DepressStatus.NORMAL:
                drawable = R.drawable.bs_checkbox_feeling_normal;
                break;
            case DepressStatus.GOOD:
                drawable = R.drawable.bs_checkbox_feeling_good;
                break;
            case DepressStatus.NICE:
                drawable = R.drawable.bs_checkbox_feeling_nice;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + depressStatus);
        }

        return drawable;
    }
}