package com.example.castroreyrobert.notesdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BackgroundTask bgtask = new BackgroundTask(this);
        bgtask.execute("view_info");
    }

    public void addButtonClicked(View view){
        startActivity(new Intent(this, SaveNote.class));
    }

}
