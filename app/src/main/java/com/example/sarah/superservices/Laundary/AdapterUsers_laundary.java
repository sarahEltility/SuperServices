package com.example.sarah.superservices.Laundary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarah.superservices.R;
import com.example.sarah.superservices.UserProfile.UserProfileActivity;
import com.example.sarah.superservices.model.Users;

import java.util.ArrayList;

/**
 * Created by Mah on 2/17/2017.
 */

public class AdapterUsers_laundary extends RecyclerView.Adapter<AdapterUsers_laundary.ViewHolder> implements View.OnClickListener{

    ArrayList<Users> arrayList;
    Context context;
    LaundaryActivity followerActivity;
    Users encap = new Users();

    public AdapterUsers_laundary(ArrayList<Users> arrayList, Context context, LaundaryActivity followerActivity) {
        this.arrayList = arrayList;
        this.context = context;
        this.followerActivity = followerActivity;
    }
    @Override
    public AdapterUsers_laundary.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View Layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_followers,parent,false);

       ViewHolder viewHolder = new ViewHolder(Layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterUsers_laundary.ViewHolder holder, int position) {

        encap = arrayList.get(position);

        holder.cardView.setTag(position);

        holder.textName.setText(encap.getName());
        holder.image.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return  arrayList.size();
    }

    @Override
    public void onClick(View view) {

    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;
        ImageView image;
        TextView textName;


        public ViewHolder(View layout) {
            super(layout);

            cardView = (CardView) layout.findViewById(R.id.list_row_container_followers);
            image = (ImageView) layout.findViewById(R.id.image_item_followers);
            textName = (TextView) layout.findViewById(R.id.fullname_item_followers);

            cardView.setOnClickListener(this);
            image.setOnClickListener(this);
            textName.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.fullname_item_followers:
                    Intent intent1 = new Intent(followerActivity, UserProfileActivity.class);
                    followerActivity.startActivity(intent1);
                    break;

                case R.id.image_item_followers:
                     intent1 = new Intent(followerActivity, UserProfileActivity.class);
                    followerActivity.startActivity(intent1);
                    break;



            }
        }

    }

}
