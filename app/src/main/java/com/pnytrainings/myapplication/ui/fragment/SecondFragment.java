package com.pnytrainings.myapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pnytrainings.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       Bundle bundle = getArguments();

        if(bundle != null){
            if(bundle.containsKey("Name")){
                String name = bundle.getString("Name");
                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
            }

            if(bundle.containsKey("age")){
                int age = bundle.getInt("age");
                Toast.makeText(getActivity(), Integer.toString(age), Toast.LENGTH_SHORT).show();
            }

            if(bundle.containsKey("cnic")){
                String cnic = bundle.getString("cnic");
                Toast.makeText(getActivity(), cnic, Toast.LENGTH_SHORT).show();
            }

        }

        return inflater.inflate(R.layout.fragment_second, container, false);
    }

}
