package com.example.flex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    String rollnumber = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        rollnumber = intent.getStringExtra("rollno");
    }

    public void goToProfile(View view){
        Intent intents = new Intent(Home.this, StudentProfile.class);
        intents.putExtra("rollno", rollnumber);
        startActivity(intents);
    }
    public void goToMarks(View view){
        Intent intents = new Intent(Home.this, Marks.class);
        intents.putExtra("rollno", rollnumber);
        startActivity(intents);
    }
    public void goToAnnouncements(View view){
        Intent intents = new Intent(Home.this, Announcement.class);
        intents.putExtra("rollno", rollnumber);
        startActivity(intents);
    }
    public void goToAttendance(View view){
        Intent intents = new Intent(Home.this, Attendance.class);
        intents.putExtra("rollno", rollnumber);
        startActivity(intents);
    }
    public void goToTranscript(View view){
        Intent intents = new Intent(Home.this, Transcript.class);
        intents.putExtra("rollno", rollnumber);
        startActivity(intents);
    }
}