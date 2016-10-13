package com.example.castroreyrobert.notesdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SaveNote extends AppCompatActivity {

    EditText etTitle, etNote;
    TextView tvDate;
    String title, note, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_note);

        etTitle = (EditText)findViewById(R.id.etTitle);
        etNote = (EditText)findViewById(R.id.etNote);
        tvDate = (TextView)findViewById(R.id.tvDate);

        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDateString = formatter.format(currentDate);
        tvDate.setText(formattedDateString);

    }

    public void saveClicked(View view){

        title = etTitle.getText().toString();
        note = etNote.getText().toString();
        date = tvDate.getText().toString();
        BackgroundTask bgTask = new BackgroundTask(this);
        bgTask.execute("add_info", title, note, date);
        finish();

        startActivity(new Intent(this, MainActivity.class));

    }

    public void cancelClicked(View view){
        finish();
    }
}
