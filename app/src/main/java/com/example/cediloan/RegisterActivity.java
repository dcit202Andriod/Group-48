package com.example.cediloan;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
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

import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "PERMISSION_TAG";
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
            public void updateDrawState( TextPaint ds) {
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

        home = (Button) findViewById(R.id.register_to_home_btn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeActivity();
            }
        });

        phoneNumber = findViewById(R.id.text_input_phoneNumber);
        createPassword = findViewById(R.id.text_input_createPassword);
        confirmPassword = findViewById(R.id.text_input_confirmPassword);
    }

    private boolean hasLocationPermission(){
        return checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }


    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private Boolean validatePhoneNumber() {
        String phoneNumberField = Objects.requireNonNull(phoneNumber.getEditText()).getText().toString().trim();

        if(phoneNumberField.isEmpty()) {
            phoneNumber.setError("Field can't be empty");
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
        } else if (createPasswordField.length() > 4) {
            createPassword.setError("Password can't be more than 4 digits");
            return false;
        } else if (createPasswordField.length() < 4) {
            createPassword.setError("Password can't be less than 4 digits");
            return false;
        } else {
            createPassword.setError(null);
            return true;
        }
    }

    private Boolean validateConfirmPassword() {
        String confirmPasswordField = Objects.requireNonNull(confirmPassword.getEditText()).getText().toString().trim();

        if(confirmPasswordField.isEmpty()) {
            confirmPassword.setError("Field can't be empty");
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
        }
    }

}