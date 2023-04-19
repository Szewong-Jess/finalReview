package com.example.finalreview.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalreview.R;
import com.example.finalreview.database.DogDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NextActivity extends AppCompatActivity {
    DogDatabase ddb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        ImageView img = findViewById(R.id.imageViewDog);
        TextView name = findViewById(R.id.textViewName);
        TextView type = findViewById(R.id.textViewType);
        TextView id = findViewById(R.id.textViewId);
        TextView dob = findViewById(R.id.textViewBod);

        Bundle bundle = getIntent().getExtras();
        String dogId = bundle.getString("ID");
        int dogPic = bundle.getInt("PICS");
        int dogName = bundle.getInt("NAME");
        String dogType = bundle.getString("TYPE");
        String dogDob = bundle.getString("dogDob");

        img.setImageResource(dogPic);
        id.setText(dogId);
        name.setText(dogName);
        type.setText(dogType);
        dob.setText(dogDob);
    }
}