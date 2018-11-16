package com.pnytrainings.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pnytrainings.myapplication.R;

public class MainActivity extends AppCompatActivity {

    TextView nameTextView;
    EditText inputEditText;
    Button outputButton;
    String mInput = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("Life Cycle Method"," : onCreate");
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        inputEditText = (EditText) findViewById(R.id.nameEditText);
        outputButton = (Button) findViewById(R.id.outputButton);

        outputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mInput = inputEditText.getText().toString();

                Intent goToNextActivity  = new Intent(MainActivity.this,SecondActivity.class);
                goToNextActivity.putExtra("Name",mInput);
                startActivity(goToNextActivity);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life Cycle Method"," : onStart");
        // load data from DB.
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life Cycle Method"," : onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life Cycle Method"," : onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life Cycle Method"," : onStop");

        //Relaesae DB instance
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life Cycle Method"," : onDestroy");
    }

}
