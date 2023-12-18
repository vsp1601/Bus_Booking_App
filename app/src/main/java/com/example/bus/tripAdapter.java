package com.example.bus;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class tripAdapter extends RecyclerView.Adapter<tripAdapter.MyViewHolder> {
    private Context context;
    private TextView tvBookingTPass, tvBookingDate;
    private ArrayList<trip> tripArrayList;
    private String strGetEmail, strGetName;
    private Intent intent;

    public tripAdapter(Context context, ArrayList<trip> tripArrayList, TextView tvBookingTPass, TextView tvBookingDate, String strGetEmail, String strGetName) {
        this.context = context;
        this.tripArrayList = tripArrayList;
        this.tvBookingTPass = tvBookingTPass;
        this.tvBookingDate = tvBookingDate;
        this.strGetEmail = strGetEmail;
        this.strGetName = strGetName;
    }

    @NonNull
    @Override
    public tripAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("hi", "hello1");
        View v = LayoutInflater.from(context).inflate(R.layout.recy_ticket_book, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        trip trip = tripArrayList.get(position);
        holder.tv_rTime.setText(trip.rTime);
        holder.tv_tTime.setText(trip.tTime);
        holder.tv_Pick.setText(trip.pick);
        holder.tv_Drop.setText(trip.drop);
        holder.tv_Price.setText(trip.price);

        intent = ((Activity) context).getIntent();

        holder.btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTPass = tvBookingTPass.getText().toString();
                String strDate = tvBookingDate.getText().toString();

                strGetEmail = intent.getStringExtra("email");
                strGetName = intent.getStringExtra("name");

                intent = new Intent(v.getContext(), PaymentActivity.class);
//                intent.putExtra("pick", trip.pick.toString());
//                intent.putExtra("pass", strTPass);
//                intent.putExtra("drop", trip.drop.toString());
                MyGlobalClass globalClass = MyGlobalClass.getInstance();
                globalClass.setBookTime(trip.rTime.toString());
                globalClass.setSharedPrice(trip.price.toString());




                //               intent.putExtra("time", trip.rTime.toString());
//                intent.putExtra("price", trip.price.toString());
//                intent.putExtra("date", strDate);
//                intent.putExtra("email", strGetEmail);
//                intent.putExtra("name", strGetName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tripArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rTime, tv_tTime, tv_Pick, tv_Drop, tv_Price;
        Button btnBooking;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_rTime = itemView.findViewById(R.id.rvTrip_rTimeID);
            tv_tTime = itemView.findViewById(R.id.rvTrip_tTimeID);
            tv_Pick = itemView.findViewById(R.id.rvTrip_PickID);
            tv_Drop = itemView.findViewById(R.id.rvTrip_tDropID);
            tv_Price = itemView.findViewById(R.id.rvTrip_PriceID);
            btnBooking = itemView.findViewById(R.id.btn_recyBookID);


        }
    }
}

