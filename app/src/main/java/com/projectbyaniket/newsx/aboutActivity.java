package com.projectbyaniket.newsx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class aboutActivity extends AppCompatActivity {

    TextView imageView;
    TextView about,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        imageView = findViewById(R.id.profile);
        about = findViewById(R.id.aboutApp);
        contact = findViewById(R.id.contact);


        about.setText("         Hi, NewsX is an Android Project created by us as Above We are IT Student at DIT ,Pune. In NewsX you " +
                "will get all Trusted News From a well Know API source For any Query you can contact Me.." );


    }
}