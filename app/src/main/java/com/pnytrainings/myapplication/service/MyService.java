package com.pnytrainings.myapplication.service;


import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import static com.pnytrainings.myapplication.ui.fragment.DemoFragmentActivity.downloadComplete;

public class MyService extends IntentService {

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        for (int i = 0; i <= 100; i++) {

            Intent sendBroadCast = new Intent(downloadComplete);
            sendBroadCast.putExtra("download",i);
            LocalBroadcastManager.getInstance(this).sendBroadcast(sendBroadCast);

        }

    }
}
