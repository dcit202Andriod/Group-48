package com.example.cediloan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private Button startApplication;
    private TextView reviewStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        reviewStatusTextView = findViewById(R.id.review_status_textview);

        String reviewStatus = getIntent().getStringExtra("review_status");

        if (reviewStatus != null) {
            reviewStatusTextView.setText(reviewStatus);
            reviewStatusTextView.setVisibility(View.VISIBLE);
        }

        startApplication = findViewById(R.id.application_btn);
        startApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormActivity1();
            }
        });
    }

    private void openFormActivity1() {
        Intent intent = new Intent(this, Form1Activity.class);
        startActivity(intent);
    }

    public void myClickHandler(View view) {
        openProfileActivity();
    }

    private void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}