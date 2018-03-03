package com.example.sarah.superservices.Requests;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarah.superservices.R;
import com.example.sarah.superservices.model.Connector;
import com.example.sarah.superservices.model.Users;

import java.util.ArrayList;

/**
 * Created by Mah on 2/20/2017.
 */

public class AdapterUsers_SentRequests extends RecyclerView.Adapter<AdapterUsers_SentRequests.ViewHolder> implements View.OnClickListener{

        static ArrayList<Users> arrayList;
        Context context;
        RequestsActivity requestsActivity;
        Users encap = new Users();

        public AdapterUsers_SentRequests(ArrayList<Users> arrayList, Context context, RequestsActivity requestsActivity) {
            this.arrayList = arrayList;
            this.context = context;
            this.requestsActivity = requestsActivity;
        }



    @Override
        public AdapterUsers_SentRequests.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View Layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_sent_request,parent,false);

          AdapterUsers_SentRequests.ViewHolder viewHolder = new AdapterUsers_SentRequests.ViewHolder(Layout);
            return viewHolder;
        }



        @Override
        public void onBindViewHolder(AdapterUsers_SentRequests.ViewHolder holder, int position) {

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
    public void removeItem(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
    }


        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            CardView cardView;
            ImageView image;
            TextView textName;
            Button button;


            public ViewHolder(View layout) {
                super(layout);

                cardView = (CardView) layout.findViewById(R.id.list_row_container_Sent_request);
                image = (ImageView) layout.findViewById(R.id.image_item_Sent_request);
                textName = (TextView) layout.findViewById(R.id.fullname_item_Sent_request);

                button = (Button)layout.findViewById(R.id.button_in_card_view_sent_request) ;
                button.setOnClickListener(this);

                cardView.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.button_in_card_view_sent_request:
                        removeItem(getAdapterPosition());
                        Connector c = new Connector(context,requestsActivity);
                        c.CancelMySentRequestes(encap.getId().toString());
                      //  Toast.makeText(view.getContext(),"HE WILL REMOVE THE ROW AND CALL REMOVE REQUEST METHOD --  "+ encap.getId().toString(),Toast.LENGTH_LONG).show();
                        break;


                }

            }

        }

    }


