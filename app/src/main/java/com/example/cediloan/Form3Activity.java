package com.example.cediloan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Form3Activity extends AppCompatActivity {

    private static final int PICK_CONTACT_REQUEST = 1;
    private static final int PICK_CONTACT_REQUEST_2 = 2;
    private ImageButton contactsImageButton1;
    private ImageButton contactsImageButton2;
    private TextInputLayout phoneNumberEditText;
    private ImageButton backToForm2;
    private Button saveAndContinue;

    String[] relation = {"Father", "Mother", "Brother", "Sister", "Friend", "Relative"};
    String[] relation1 = {"Father", "Mother", "Brother", "Sister", "Friend", "Relative"};
    AutoCompleteTextView autoCompleteRelation;
    AutoCompleteTextView autoCompleteRelation1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);

        contactsImageButton1 = findViewById(R.id.contacts_image_btn);
        contactsImageButton2 = findViewById(R.id.contacts_image_btn2);
        phoneNumberEditText = findViewById(R.id.editText_phoneNumber1);
        phoneNumberEditText = findViewById(R.id.editText_phoneNumber2);

        contactsImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContacts(PICK_CONTACT_REQUEST);
            }
        });

        contactsImageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContacts(PICK_CONTACT_REQUEST_2);
            }
        });

        backToForm2 = findViewById(R.id.back_button);
        backToForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormActivity2();
            }
        });


        autoCompleteRelation = findViewById(R.id.relation);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, relation);
        autoCompleteRelation.setThreshold(2);
        autoCompleteRelation.setAdapter(adapter);

        autoCompleteRelation1 = findViewById(R.id.relation1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.list_item, relation1);
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

    private void openContacts(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        resultLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri contactUri = data.getData();
                        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
                        Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                            int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                            String contactName = cursor.getString(nameColumnIndex);
                            String contactNumber = cursor.getString(numberColumnIndex);
                            cursor.close();

                            // Set the contact name and number in the corresponding fields
                            if (result.getResultCode() == PICK_CONTACT_REQUEST) {
                                TextInputEditText editTextName1 = findViewById(R.id.editText_name1);
                                TextInputEditText editTextPhoneNumber1 = findViewById(R.id.editText_phoneNumber1);
                                editTextName1.setText(contactName);
                                editTextPhoneNumber1.setText(contactNumber);
                            } else if (result.getResultCode() == PICK_CONTACT_REQUEST_2) {
                                TextInputEditText editTextName2 = findViewById(R.id.editText_name2);
                                TextInputEditText editTextPhoneNumber2 = findViewById(R.id.editText_phoneNumber2);
                                editTextName2.setText(contactName);
                                editTextPhoneNumber2.setText(contactNumber);
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "No contact selected", Toast.LENGTH_SHORT).show();
                }
            }
    );


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String contactName = cursor.getString(nameColumnIndex);
                String contactNumber = cursor.getString(numberColumnIndex);
                cursor.close();

                // Set the contact name and number in the corresponding fields
                if (requestCode == PICK_CONTACT_REQUEST) {
                    TextInputEditText editTextName1 = findViewById(R.id.editText_name1);
                    TextInputEditText editTextPhoneNumber1 = findViewById(R.id.editText_phoneNumber1);
                    editTextName1.setText(contactName);
                    editTextPhoneNumber1.setText(contactNumber);
                } else if (requestCode == PICK_CONTACT_REQUEST_2) {
                    TextInputEditText editTextName2 = findViewById(R.id.editText_name2);
                    TextInputEditText editTextPhoneNumber2 = findViewById(R.id.editText_phoneNumber2);
                    editTextName2.setText(contactName);
                    editTextPhoneNumber2.setText(contactNumber);
                }
            }
        } else {
            Toast.makeText(this, "No contact selected", Toast.LENGTH_SHORT).show();
        }
    }


    private void openFormActivity2() {
        Intent intent = new Intent(this, FormActivity2.class);
        startActivity(intent);
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("review_status", "Your application is under review. Check back in a few minutes.");
        startActivity(intent);
    }
}
