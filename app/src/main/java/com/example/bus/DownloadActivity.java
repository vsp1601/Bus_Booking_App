package com.example.bus;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DownloadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyGlobalClass globalClass = MyGlobalClass.getInstance();
        String sharedFrom = globalClass.getSharedFrom();
        String sharedTo = globalClass.getSharedTo();
        String sharedDate = globalClass.getBookDate();
        String sharedTime = globalClass.getBookTime();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        TextView myTextView = findViewById(R.id.dndTvFromID);
        myTextView.setText(sharedFrom);

        TextView myTextView1 = findViewById(R.id.dndTvToID);
        myTextView1.setText(sharedTo);

        TextView myTextView2 = findViewById(R.id.dndTvDateID);
        myTextView2.setText(sharedDate);

        TextView myTextView3 = findViewById(R.id.dndTvTimeID);
        myTextView3.setText(sharedTime);

    }
}
