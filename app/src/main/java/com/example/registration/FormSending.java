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

public class FormSending extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    private EditText editTextNumber,editTextComment;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sending);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        editTextComment = (EditText) findViewById(R.id.editTextComment);
        buttonSend = (Button) findViewById(R.id.buttonSend);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonSend.setOnClickListener(this);
    }

    private void saveUserReq(){
        String number = editTextNumber.getText().toString().trim();
        String comm = editTextComment.getText().toString().trim();

        Requests requests = new Requests(number,comm);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child("requests").child(user.getUid()).setValue(requests);

        Toast.makeText(this,"request form send ..." , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSend){
            saveUserReq();
            finish();
            startActivity(new Intent(this, RecyclerViewConf.class));
        }
    }
}
