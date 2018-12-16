package com.pnytrainings.myapplication.ui.volley;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pnytrainings.myapplication.R;
import com.pnytrainings.myapplication.model.Customer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by PNY Trainings on 12/16/2018.
 */

 class UserAdapter  extends  RecyclerView.Adapter<UserAdapter.UserViewHolder>  {

    private Activity activity;
    private ArrayList<Customer> customers;

    public UserAdapter(Activity activity, ArrayList<Customer> customers) {
        this.activity = activity;
        this.customers = customers;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout mainVIew;
        ImageView pp;
        TextView firstName , lastName;

         UserViewHolder(View itemView) {
            super(itemView);
            mainVIew = itemView.findViewById(R.id.Rl);
            pp = itemView.findViewById(R.id.pp);
            firstName = itemView.findViewById(R.id.name);
            lastName = itemView.findViewById(R.id.lastname);
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        Customer customer = customers.get(position);

        holder.firstName.setText(customer.getFname());
        holder.lastName.setText(customer.getLname());

        Picasso.with(activity).load("http://epay.cybussolutions.com/epay/" + customer.getProfile_pic().trim()).into(holder.pp);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

}
