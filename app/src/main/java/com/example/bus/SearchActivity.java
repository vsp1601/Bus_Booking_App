package com.example.bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sFROM, sTO, sTYPE;
    String strFROM, strTo, strType, strTotalPass, strDate, strLogEmail, strUserName;
    int passValue;
    Button btnFindRide;
    ImageButton iBtnPlus, iBtnMin;
    ImageView btnDatePicker;
    TextView tvPass, tvDate, tvName;
    Intent intent;
    FirebaseFirestore firebaseFirestore;
    DocumentReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        intent = getIntent();
        strLogEmail = intent.getStringExtra("email");

        firebaseFirestore = FirebaseFirestore.getInstance();
        reference = firebaseFirestore.collection("UserProfile").document(strLogEmail);

        tvPass = findViewById(R.id.tv_passengersID);
        iBtnPlus = findViewById(R.id.imgBtn_plusID);
        iBtnMin = findViewById(R.id.imgBtn_minusID);
        sFROM = findViewById(R.id.spinnerFromID);
        sTO = findViewById(R.id.spinnerToID);
        sTYPE = findViewById(R.id.spinner_busTypeID);
        btnFindRide = findViewById(R.id.btn_findRideID);
        btnDatePicker = findViewById(R.id.datePickerID);
        tvDate = findViewById(R.id.tvDateTravelID);
        tvName = findViewById(R.id.tvUserName_ID);

        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    strUserName = documentSnapshot.getString("Name");
                    tvName.setText("Hi, Mr. " + strUserName);
                }
            }
        });
        ArrayAdapter<CharSequence> adapterFROM = ArrayAdapter.createFromResource(this, R.array.travel_from, R.layout.search_from);
        adapterFROM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sFROM.setAdapter(adapterFROM);
        sFROM.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterTO = ArrayAdapter.createFromResource(this, R.array.travel_to, R.layout.search_to);
        adapterTO.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTO.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTO.setAdapter(adapterTO);
        sTO.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterBUSTYPE = ArrayAdapter.createFromResource(this, R.array.bus_type, R.layout.search_type);
        adapterBUSTYPE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTYPE.setAdapter(adapterBUSTYPE);
        sTYPE.setOnItemSelectedListener(this);

        iBtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strTotalPass = tvPass.getText().toString();
                passValue = Integer.parseInt(strTotalPass);
                passValue++;
                tvPass.setText(String.valueOf(passValue));
            }
        });
        iBtnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strTotalPass = tvPass.getText().toString();
                passValue = Integer.parseInt(strTotalPass);
                passValue--;
                tvPass.setText(String.valueOf(passValue));
            }
        });
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Travel Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();

                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        strDate = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()).format(new Date(selection));
                        tvDate.setText(MessageFormat.format("(0)", strDate));
                    }
                });
                materialDatePicker.show(getSupportFragmentManager(), "tag");
            }
        });
        btnFindRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strFROM = sFROM.getSelectedItem().toString();
                strTo = sTO.getSelectedItem().toString();
                strType = sTYPE.getSelectedItem().toString();
                int tPass = Integer.parseInt(strTotalPass) + 1;
                String strTPass = String.valueOf(tPass);
                Intent intent = new Intent(SearchActivity.this, Booking.class);
                intent.putExtra("from", strFROM);
                intent.putExtra("to", strTo);
                intent.putExtra("date", strDate);
                intent.putExtra("tPass", strTPass);
                intent.putExtra("email", strLogEmail);
                intent.putExtra("name", strUserName);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}