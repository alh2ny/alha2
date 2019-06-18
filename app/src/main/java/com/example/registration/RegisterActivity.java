package com.example.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextRePassword;
    private EditText editTextFullName;
    private EditText editTextNationalId;
    private Button buttonSignup;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        buttonSignup = (Button) findViewById(R.id.buttonSignup);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextRePassword = (EditText) findViewById(R.id.editTextRePassword);
        editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        editTextNationalId = (EditText) findViewById(R.id.editTextNationalId);


        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    public void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String repassword = editTextRePassword.getText().toString().trim();
        String fullname = editTextFullName.getText().toString().trim();
        String nationalid = editTextNationalId.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "please enter your Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "please enter your Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(repassword)) {
            //password is empty
            Toast.makeText(this, "please enter your Repassword", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(fullname)) {
            //password is empty
            Toast.makeText(this, "please enter your Full Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(nationalid)) {
            //password is empty
            Toast.makeText(this, "please enter your National ID", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Loading To Register... ");

        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "couldn't register", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSignup) {
            registerUser();
        }

       if (view == textViewSignin) {
            //will open signup later
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}