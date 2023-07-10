package com.example.cediloan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.NumberPicker;

public class FormActivity2 extends AppCompatActivity {

    private Button saveAndContinue;

    String[] educationalLevel = {"Junior High", "Senior High", "University/College", "Other"};
    String[] region = {"Greater Accra", "Central", "Western", "Volta", "Eastern", "Ashanti", "Brong-Ahafo", "Northern", "Upper East", "Upper West"};
    AutoCompleteTextView autoCompleteEducationalLevel;
    AutoCompleteTextView autoCompleteRegion;
    ArrayAdapter<String> adapterEducationalLevel;
    ArrayAdapter<String> adapterRegion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

    saveAndContinue = findViewById(R.id.save_continue_btn);
    saveAndContinue.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openForm3Activity();
        }
    });

        autoCompleteEducationalLevel = findViewById(R.id.educational_level);
        adapterEducationalLevel = new ArrayAdapter<String>(this, R.layout.list_item, educationalLevel);
        autoCompleteEducationalLevel.setAdapter(adapterEducationalLevel);
        autoCompleteEducationalLevel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });
        autoCompleteRegion = findViewById(R.id.region);
        adapterRegion = new ArrayAdapter<String>(this, R.layout.list_item, region);
        autoCompleteRegion.setAdapter(adapterRegion);
        autoCompleteRegion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });
    }

    private void openForm3Activity() {
        Intent intent = new Intent(this, Form3Activity.class);
        startActivity(intent);
    }
}