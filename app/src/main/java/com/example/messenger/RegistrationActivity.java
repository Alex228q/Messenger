package com.example.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editTextPassword;
    private EditText editTextLatName;
    private EditText editTextAge;
    private Button buttonSignUp;
    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        observeViewModel();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getTrimmedValue(editTextName);
                String password = getTrimmedValue(editTextPassword);
                String lastName = getTrimmedValue(editTextLatName);
                String email = getTrimmedValue(editTextEmail);
                int age = Integer.parseInt(getTrimmedValue(editTextAge));
                viewModel.signUp(email, password, name, lastName, age);
            }
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null) {
                    Toast.makeText(RegistrationActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    startActivity(UsersActivity.newIntent(RegistrationActivity.this, firebaseUser.getUid()));
                    finish();
                }
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