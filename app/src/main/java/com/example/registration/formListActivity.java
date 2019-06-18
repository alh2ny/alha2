package com.example.registration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class formListActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_list);

        mRecycleView = (RecyclerView) findViewById(R.id.recycleViewBooks);
        new FirebaseDatabaseHelper().readForm(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Requests> requests, List<String> keys) {
                findViewById(R.id.progressBarBook).setVisibility(View.GONE);
                new RecyclerViewConf().setconfig(mRecycleView,formListActivity.this,requests,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
