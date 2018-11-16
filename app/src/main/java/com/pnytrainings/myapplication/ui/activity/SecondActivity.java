package com.pnytrainings.myapplication.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.pnytrainings.myapplication.R;

public class SecondActivity extends AppCompatActivity {

    String data= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent fromPreviousActivity = getIntent();
        if(fromPreviousActivity !=null){
            if(fromPreviousActivity.hasExtra("Name")){
                data = fromPreviousActivity.getStringExtra("Name");
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
