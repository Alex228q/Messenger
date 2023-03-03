package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editTextPassword;
    private EditText editTextLatName;
    private EditText editTextAge;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getTrimmedValue(editTextName);
                String password = getTrimmedValue(editTextPassword);
                String lastName = getTrimmedValue(editTextLatName);
                String email = getTrimmedValue(editTextEmail);
                int age = Integer.parseInt(getTrimmedValue(editTextAge));
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.editTextRegisterEmail);
        editTextName = findViewById(R.id.editTextRegisterName);
        editTextPassword = findViewById(R.id.editTextRegisterPassword);
        editTextLatName = findViewById(R.id.editTextRegisterLastName);
        editTextAge = findViewById(R.id.editTextRegisterAge);
        buttonSignUp = findViewById(R.id.buttonSignUp);
    }

    private String getTrimmedValue(EditText editText) {
        return editText.getText().toString().trim();
    }
}