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

public class AdapterUsers_receiveRequest extends RecyclerView.Adapter<AdapterUsers_receiveRequest.ViewHolder> implements View.OnClickListener{

    static ArrayList<Users> arrayList;
    Context context;
    RequestsActivity requestsActivity;
    Users encap = new Users();

    public AdapterUsers_receiveRequest(ArrayList<Users> arrayList, Context context, RequestsActivity requestsActivity) {
        this.arrayList = arrayList;
        this.context = context;
        this.requestsActivity = requestsActivity;
    }
    @Override
    public AdapterUsers_receiveRequest.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View Layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_receive_request,parent,false);

        AdapterUsers_receiveRequest.ViewHolder viewHolder = new AdapterUsers_receiveRequest.ViewHolder(Layout);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(AdapterUsers_receiveRequest.ViewHolder holder, int position) {

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
        TextView button2;



        public ViewHolder(View layout) {
            super(layout);

            cardView = (CardView) layout.findViewById(R.id.list_row_container_receive_request);
            image = (ImageView) layout.findViewById(R.id.image_item_receive_request);
            textName = (TextView) layout.findViewById(R.id.fullname_item_receive_request);

            button = (Button)layout.findViewById(R.id.button_in_card_view_receive_request) ;
            button.setOnClickListener(this);

            button2 = (TextView) layout.findViewById(R.id.button_in_card_view_receive_request_canel) ;
            button2.setOnClickListener(this);

            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Connector c;
            switch (view.getId()) {
                case R.id.button_in_card_view_receive_request:
                   removeItem(getAdapterPosition());
                     c = new Connector(context,requestsActivity);
                    c.ConfirmRequestes(encap.getId().toString());


                   //  Toast.makeText(view.getContext(),"HE WILL REMOVE THE ROW AND CALL Confirm REQUEST METHOD --  "+ encap.getId().toString(),Toast.LENGTH_LONG).show();
                    break;
                case R.id.button_in_card_view_receive_request_canel:
                     removeItem(getAdapterPosition());
                     c = new Connector(context,requestsActivity);
                    c.RejectRequestes(encap.getId().toString());

                    // Toast.makeText(view.getContext(),"HE WILL REMOVE THE ROW AND CALL Remove Coming REQUEST METHOD --  "+ encap.getId().toString(),Toast.LENGTH_LONG).show();
                    break;


            }

        }

    }

}


