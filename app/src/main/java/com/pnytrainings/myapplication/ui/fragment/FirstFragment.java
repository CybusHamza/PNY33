package com.pnytrainings.myapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pnytrainings.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    Bundle bundle;
    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_blank, container, false);


        bundle = getArguments();

        if(bundle != null){
            if(bundle.containsKey("Name")){
                String name = bundle.getString("Name");
                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
            }

            if(bundle.containsKey("age")){
                int age = bundle.getInt("age");
                Toast.makeText(getActivity(), Integer.toString(age), Toast.LENGTH_SHORT).show();
            }

        }
        TextView gotoNext = v.findViewById(R.id.goToNext);

        gotoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("cnic","12312413123");

                SecondFragment secondFragment = new SecondFragment() ;
                secondFragment.setArguments(bundle);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.container,secondFragment).addToBackStack("first");
                transaction.commit();

            }
        });


        return v;
    }

}
