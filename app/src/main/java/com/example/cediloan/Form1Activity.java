package com.example.cediloan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Calendar;

public class Form1Activity extends AppCompatActivity {
    private Button saveAndContinue1;
    private static final String TAG = "FormActivity1";
    String[] occupations = {"Accountant/Banker", "Architect/Dentist/Lawyer", "Student/Waiter(es)", "Carpenter/Chef/Police Officer", "Doctor/Nurse", "Engineer/Electrician/Plumber", "Hairdresser/Trader/Farmer"};
    AutoCompleteTextView autoCompleteOccupation;
    ArrayAdapter<String> adapterOccupations;

    String[] income = {"Below - 400", "401 - 701", "701 - 1000", "1001 - above"};
    AutoCompleteTextView autoCompleteIncome;
    ArrayAdapter<String> adapterIncome;
    private Button mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);

        mDisplayDate = findViewById(R.id.datePickerButton);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender = Calendar.getInstance();
                int year = calender.get(Calendar.YEAR);
                int month = calender.get(Calendar.MONTH);
                int day = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Form1Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        autoCompleteOccupation = findViewById(R.id.auto_complete_occupation);
        adapterOccupations = new ArrayAdapter<String>(this, R.layout.list_item, occupations);
        autoCompleteOccupation.setAdapter(adapterOccupations);
        autoCompleteOccupation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item:" +item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteIncome = findViewById(R.id.auto_complete_income);
        adapterIncome = new ArrayAdapter<String>(this, R.layout.list_item, income);
        autoCompleteIncome.setAdapter(adapterIncome);
        autoCompleteIncome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        NumberPicker numberPicker = findViewById(R.id.salary_paday_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(31);

        saveAndContinue1 = findViewById(R.id.save_continue_btn);
        saveAndContinue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormActivity2();
            }
        });
    }

    private void openFormActivity2() {
        Intent intent = new Intent(this, FormActivity2.class);
        startActivity(intent);
    }
}