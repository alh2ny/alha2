package com.example.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private Button buttonLogout;

    private DatabaseReference databaseReference;

    private EditText editTextName,editTextAddress;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("welcome "+user.getEmail());


        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);

        buttonSave.setOnClickListener(this);
    }

    private void saveUserInformation(){
        String name = editTextName.getText().toString().trim();
        String add = editTextAddress.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name,add);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child("users").child(user.getUid()).setValue(userInformation);

        Toast.makeText(this,"Information Saved ..." , Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        if(view == buttonSave){
            saveUserInformation();
            finish();
            startActivity(new Intent(this,FormSending.class));
        }
    }
}
