package com.pnytrainings.myapplication.ui.shared_prefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.pnytrainings.myapplication.R;

public class SharedPrefActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);

        input = (EditText) findViewById(R.id.name);

        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);String strInput  = sharedPreferences.getString("input","No value Found");

        input.setText(strInput);

    }

    public void savePref(View view) {
        editor = sharedPreferences.edit();

        editor.putString("input",input.getText().toString());
        editor.apply();
    }
}
