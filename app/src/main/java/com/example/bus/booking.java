package com.example.bus;

import android.os.Bundle;
import android.view.WindowManager;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.EventListener;


import java.util.ArrayList;
//import java.util.EventListener;

public class Booking extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvBookFrom, tvBookTo, tvBookDate, tvBookPass;

    ArrayList<trip> arrayList;
    tripAdapter adapter;
    FirebaseFirestore db;
    Intent intent;
    String strGetFrom, strGetTo, strGetDate, strGetPass, strGetEmail, strGetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        strGetFrom = intent.getStringExtra("from");
        strGetTo = intent.getStringExtra("to");
        strGetDate = intent.getStringExtra("date");
        strGetPass = intent.getStringExtra("tPass");
        strGetEmail = intent.getStringExtra("email");
        strGetName = intent.getStringExtra("name");

        recyclerView = findViewById(R.id.recy_tripID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvBookFrom = findViewById(R.id.tv_bookingFromID);
        tvBookTo = findViewById(R.id.tv_bookingToID);
        tvBookDate = findViewById(R.id.tv_bookingDateID);
        tvBookPass = findViewById(R.id.tv_bookingPassID);

        tvBookFrom.setText(strGetFrom);
        tvBookTo.setText(strGetTo);
        tvBookDate.setText(strGetDate);
        tvBookPass.setText(strGetPass);

        db = FirebaseFirestore.getInstance();

        arrayList = new ArrayList<trip>();
        adapter = new tripAdapter(Booking.this, arrayList, tvBookPass, tvBookDate, strGetEmail, strGetName);
        recyclerView.setAdapter(adapter);

        if (strGetFrom.matches("Ettimadai") && strGetTo.matches("Gandhipuram")) {
            DhkSyl();
        } else if (strGetFrom.matches("Vadavalli") && strGetTo.matches("Ettimadai")) {
            KhlRaj();
        }
    }

    private void DhkSyl() {
        db.collection("DhkSyl")
                .orderBy("Time", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            // Handle the error, e.g., log it or display an error message
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                arrayList.add(dc.getDocument().toObject(trip.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }


    private void KhlRaj() {
        db.collection("KhlRaj")
                .orderBy("Time", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            // Handle the error, e.g., log it or display an error message
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                arrayList.add(dc.getDocument().toObject(trip.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                        
                    }
                });
    }

}

