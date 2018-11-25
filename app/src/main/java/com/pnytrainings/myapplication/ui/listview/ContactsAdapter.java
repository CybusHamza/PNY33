package com.pnytrainings.myapplication.ui.listview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pnytrainings.myapplication.R;

import java.util.ArrayList;

/**
 * Created by PNY Trainings on 11/25/2018.
 */

public class ContactsAdapter extends ArrayAdapter{

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> phone = new ArrayList<>();
    ArrayList<Integer> imgs = new ArrayList<>();
    Context context;
    int resource;

    private LayoutInflater layoutInflater;

    public ContactsAdapter(@NonNull Context context, @LayoutRes int resource , ArrayList<String> name, ArrayList<String> phone,ArrayList<Integer> imgs) {
        super(context, resource);

        this.names = name ;
        this.phone = phone;
        this.context = context;
        this.imgs = imgs;
        this.resource = resource;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = layoutInflater.inflate(resource,parent,false);

        ImageView pp = v.findViewById(R.id.pp);
        TextView  name =  v.findViewById(R.id.Name);
        TextView contactNumber = v.findViewById(R.id.contactNum);

        name.setText(names.get(position));
        contactNumber.setText(phone.get(position));
        pp.setImageResource(imgs.get(position));

     /*   v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "name:" + names.get(position) +
                        "\n phone:" + phone.get(position) +
                        "\n imgs:" + imgs.get(position) +
                        "\n position:" + position , Toast.LENGTH_SHORT).show();
            }
        });*/

        return v;
    }


}
