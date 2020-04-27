package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences  = getSharedPreferences("Notes", MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent launchListActivity = new Intent(getApplicationContext(), NotesListActivity.class);
                launchListActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                launchListActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(launchListActivity);
            }
        }, 2000);
    }
}
