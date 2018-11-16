package com.pnytrainings.myapplication.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.pnytrainings.myapplication.R;
import com.pnytrainings.myapplication.reciever.MyTImeChangeReciever;
import com.pnytrainings.myapplication.service.MyService;

public class DemoFragmentActivity extends AppCompatActivity {

    IntentFilter filter,downloadFilter;
    MyTImeChangeReciever myTImeChangeReciever;
    public static final String downloadComplete = "com.pnytrainings.myapplication.download";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_fragment);

        filter   = new IntentFilter(Intent.ACTION_TIME_TICK);
        myTImeChangeReciever = new MyTImeChangeReciever();
        downloadFilter = new IntentFilter(downloadComplete);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    BroadcastReceiver downloadBroadCastReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent !=null){
                if(intent.hasExtra("download")){
                    int per = intent.getIntExtra("download",0);
                    Log.e("Download %:",per+"");
                }
            }
        }
    };

    public void launchFragment(View view) {

        Bundle bundle = new Bundle();
        bundle.putString("Name","Hamza");
        bundle.putInt("age",30);

        FirstFragment firstFragment = new FirstFragment();
        firstFragment.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container,firstFragment);
        transaction.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myTImeChangeReciever,filter);
        LocalBroadcastManager.getInstance(this).registerReceiver(downloadBroadCastReciever,downloadFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myTImeChangeReciever);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(downloadBroadCastReciever);
    }
}
