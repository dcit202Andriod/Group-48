package com.example.cediloan;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private Button home;
    private TextInputLayout phoneNumber;
    private TextInputLayout createPassword;
    private TextInputLayout confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView textView = findViewById(R.id.resend_code);
        String text = "Did not receive code? Resend";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(RegisterActivity.this, "Sent", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };

        int startIndex = text.indexOf("Resend");
        int endIndex = startIndex + "Resend".length();
        ss.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        TextInputEditText editTextPhoneNumber = findViewById(R.id.editText_phoneNumber);
        TextInputEditText editTextCreatePassword = findViewById(R.id.editText_createPassword);
        TextInputEditText editTextConfirmPassword = findViewById(R.id.editText_confirmPassword);
        home = (Button) findViewById(R.id.register_to_home_btn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = editTextPhoneNumber.getText().toString();
                String input2 = editTextCreatePassword.getText().toString();
                String input3 = editTextConfirmPassword.getText().toString();

                if (input1.isEmpty() || input2.isEmpty() || input3.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    String toastMessage = "Phone number: " + input1 + "\nPassword: " + input3;
                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                    confirmInput(view);
                    openHomeActivity();
                }
            }
        });


        phoneNumber = findViewById(R.id.text_input_phoneNumber);
        createPassword = findViewById(R.id.text_input_createPassword);
        confirmPassword = findViewById(R.id.text_input_confirmPassword);

        phoneNumber.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    validatePhoneNumber();
                }
            }
        });

        createPassword.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    validatePhoneNumber();
                    validateCreatePassword();
                }
            }
        });

        confirmPassword.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    validatePhoneNumber();
                    validateCreatePassword();
                    validateConfirmPassword();
                }
            }
        });
    }


    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private Boolean validatePhoneNumber() {
        String phoneNumberField = Objects.requireNonNull(phoneNumber.getEditText()).getText().toString().trim();

        if (phoneNumberField.isEmpty()) {
            phoneNumber.setError("Field can't be empty");
            return false;
        } else if (phoneNumberField.length() < 10 || phoneNumberField.length() > 10) {
            phoneNumber.setError("Phone number must be 10 digits");
            return false;
        } else {
            phoneNumber.setError(null);
            return true;
        }
    }

    private Boolean validateCreatePassword() {
        String createPasswordField = Objects.requireNonNull(createPassword.getEditText()).getText().toString().trim();

        if (createPasswordField.isEmpty()) {
            createPassword.setError("Field can't be empty");
            return false;
        } else if (createPasswordField.length() != 4) {
            createPassword.setError("Password must have exactly four digits");
            return false;
        } else {
            createPassword.setError(null);
            return true;
        }
    }

    private Boolean validateConfirmPassword() {
        String confirmPasswordField = Objects.requireNonNull(confirmPassword.getEditText()).getText().toString().trim();
        String createPasswordField = Objects.requireNonNull(createPassword.getEditText()).getText().toString().trim();

        if (confirmPasswordField.isEmpty()) {
            confirmPassword.setError("Field can't be empty");
            return false;
        } else if (confirmPasswordField.length() != 4) {
            confirmPassword.setError("Password must have exactly four digits");
            return false;
        } else if (!confirmPasswordField.equals(createPasswordField)) {
            confirmPassword.setError("Passwords do not match");
            return false;
        } else {
            confirmPassword.setError(null);
            return true;
        }
    }


    public void confirmInput(View v) {
        if (validatePhoneNumber() && validateCreatePassword() && validateConfirmPassword()) {
            String input = "Phone number: " + Objects.requireNonNull(phoneNumber.getEditText()).getText().toString().trim() + "\n";
            input += "Create password: " + Objects.requireNonNull(createPassword.getEditText()).getText().toString().trim() + "\n";
            input += "Confirm password: " + Objects.requireNonNull(confirmPassword.getEditText()).getText().toString().trim() + "\n";

            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

            openHomeActivity();
        }
    }
}
