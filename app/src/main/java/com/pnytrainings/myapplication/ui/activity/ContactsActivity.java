package com.pnytrainings.myapplication.ui.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pnytrainings.myapplication.R;
import com.pnytrainings.myapplication.ui.database.DataBase;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    Button insert,fetch,update,delete;
    String strName,strPhone;
    EditText name,number;
    DataBase dataBase;




    ArrayList<String> id  =  new ArrayList<>();

    ArrayList<String> names =  new ArrayList<>();

    ArrayList<String> phione  =  new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        dataBase = new DataBase(ContactsActivity.this);

        dataBase.open();

        insert = (Button) findViewById(R.id.submit);
        fetch = (Button) findViewById(R.id.fetch);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);

        name = (EditText) findViewById(R.id.naem);
        number = (EditText) findViewById(R.id.phone);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strName = name.getText().toString();
                strPhone = number.getText().toString();

                dataBase.insert(strName,strPhone);
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id.clear();
                names.clear();;
                phione.clear();

                Cursor cursor = dataBase.fetch();
                do
                {
                    if (cursor != null) {

                        id.add(cursor.getString(0));
                        names.add(cursor.getString(1));
                        phione.add(cursor.getString(2));

                    }
                } while(cursor.moveToNext());

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strName = name.getText().toString();
                strPhone = number.getText().toString();

                dataBase.update(1,strName,strPhone);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataBase.delete(1);

            }
        });
    }
}
