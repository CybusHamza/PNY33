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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.pnytrainings.myapplication.R;
import com.pnytrainings.myapplication.reciever.MyTImeChangeReciever;
import com.pnytrainings.myapplication.service.MyService;

import java.util.ArrayList;
import java.util.List;

public class DemoFragmentActivity extends AppCompatActivity {

    IntentFilter filter,downloadFilter;
    MyTImeChangeReciever myTImeChangeReciever;
    public static final String downloadComplete = "com.pnytrainings.myapplication.download";
    private Spinner spinner1, spinner2;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);

        addItemsOnSpinner2();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        filter   = new IntentFilter(Intent.ACTION_TIME_TICK);
        myTImeChangeReciever = new MyTImeChangeReciever();
        downloadFilter = new IntentFilter(downloadComplete);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DemoFragmentActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

     private  class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();

            if(pos == 0 ){
                addItemsOnSpinnerPAK();
            }else {
                addItemsOnSpinnerIND();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

    public void addItemsOnSpinnerPAK() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("Punjab");
        list.add("KPK");
        list.add("Sindh");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        spinner2.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinnerIND() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("RAJ");
        list.add("MUMBAI");
        list.add("Delhi");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        spinner2.setAdapter(dataAdapter);
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
