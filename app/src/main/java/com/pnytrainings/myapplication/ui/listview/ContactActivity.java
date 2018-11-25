package com.pnytrainings.myapplication.ui.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.pnytrainings.myapplication.R;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    GridView contactsLV;

    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> phone = new ArrayList<>();
    ArrayList<Integer> imgs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        populateData();
        initView();

    }

    public void initView(){
        contactsLV = (GridView) findViewById(R.id.contactsLV);

        ContactsAdapter contactsAdapter = new ContactsAdapter(this,R.layout.row_contacts,name,phone,imgs);
        contactsLV.setAdapter(contactsAdapter);

        contactsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(ContactActivity.this, "name:" + name.get(pos) +
                        "\n phone:" + phone.get(pos) +
                        "\n imgs:" + imgs.get(pos) +
                        "\n position:" + pos , Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void populateData() {

        name.add("hamza");
        name.add("moiz");
        name.add("farhan");
        name.add("ali");

        phone.add("123");
        phone.add("145");
        phone.add("567");
        phone.add("987");

        imgs.add(R.drawable.avatar1);
        imgs.add(R.drawable.avatar2);
        imgs.add(R.drawable.avatar3);
        imgs.add(R.drawable.avatar4);

        name.add("hamza");
        name.add("moiz");
        name.add("farhan");
        name.add("ali");

        phone.add("123");
        phone.add("145");
        phone.add("567");
        phone.add("987");

        imgs.add(R.drawable.avatar1);
        imgs.add(R.drawable.avatar2);
        imgs.add(R.drawable.avatar3);
        imgs.add(R.drawable.avatar4);

        name.add("hamza");
        name.add("moiz");
        name.add("farhan");
        name.add("ali");

        phone.add("123");
        phone.add("145");
        phone.add("567");
        phone.add("987");

        imgs.add(R.drawable.avatar1);
        imgs.add(R.drawable.avatar2);
        imgs.add(R.drawable.avatar3);
        imgs.add(R.drawable.avatar4);

    }

}
