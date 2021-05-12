package com.example.paindiary.ui.painData;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Time to record your data!", Toast.LENGTH_LONG).show();
    }

}