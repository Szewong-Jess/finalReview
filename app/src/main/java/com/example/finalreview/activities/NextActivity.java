package com.example.finalreview.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalreview.R;

public class NextActivity extends AppCompatActivity {

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
        String dogName = bundle.getString("NAME");
        String dogType = bundle.getString("TYPE");
        String dogDob = bundle.getString("dogDob");

        img.setImageResource(dogPic);
        id.setText(dogId);
        name.setText(dogName);
        type.setText(dogType);
        dob.setText(dogDob);

    }
}