package com.example.cediloan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Form3Activity extends AppCompatActivity {
    private ImageButton backToForm2;
    private TextInputLayout textInputPhoneNumber1;
    private Button saveAndContinue;

    String[] relation = {"Father", "Mother", "Brother", "Sister", "Friend", "Relative"};
    String[] relation1 = {"Father", "Mother", "Brother", "Sister", "Friend", "Relative"};
    AutoCompleteTextView autoCompleteRelation;
    AutoCompleteTextView autoCompleteRelation1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);

        backToForm2 = findViewById(R.id.back_button);
        backToForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormActivity2();
            }
        });

        textInputPhoneNumber1 = findViewById(R.id.text_input_phoneNumber1);
        textInputPhoneNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContacts();
            }
        });

        autoCompleteRelation = findViewById(R.id.relation);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, relation);
        autoCompleteRelation.setThreshold(2);
        autoCompleteRelation.setAdapter(adapter);

        autoCompleteRelation1 = findViewById(R.id.relation1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.list_item, relation1);
        autoCompleteRelation1.setThreshold(2);
        autoCompleteRelation1.setAdapter(adapter1);

        saveAndContinue = findViewById(R.id.save_continue_btn);
        saveAndContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });


    }

    private void openContacts() {
        Intent contacts = new Intent(Intent.ACTION_MAIN);
        contacts.addCategory(Intent.CATEGORY_LAUNCHER);
        contacts.setClassName("com.Contacts.com.google.android.contacts", "com.Contacts.com.google.android.contacts.MainActivity");
        startActivity(contacts);
    }

    private void openFormActivity2() {
        Intent intent = new Intent(this, FormActivity2.class);
        startActivity(intent);
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("review_status", "Application is under review. Check back in 5 minutes");
        startActivity(intent);
    }
}