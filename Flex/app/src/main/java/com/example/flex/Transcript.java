package com.example.flex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Transcript extends AppCompatActivity {
    String rollnumber = "", response = "";
    public SemesterAdapter mAdapter;
    private Handler mainHandler = new Handler();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcript);

        listView = (ListView) findViewById(R.id.t_list);
        Intent intent = getIntent();
        rollnumber = intent.getStringExtra("rollno");

        startThread();
    }
    public ArrayList<Semester> parse(String response){
        ArrayList<String> data = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(response, "@@@");
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());

        ArrayList<Semester> semesters = new ArrayList<Semester>();
        ArrayList<Course> courses = new ArrayList<Course>();
        for (int i=0;i<data.size();i++){
            StringTokenizer tokenizer1 = new StringTokenizer(data.get(i), "%%");
            for (int j=0;j<5;j++){
                String temp = tokenizer1.nextToken();
                StringTokenizer tokenizer2 = new StringTokenizer(temp, "$");
                Course c = new Course(tokenizer2.nextToken(), tokenizer2.nextToken(), tokenizer2.nextToken(), tokenizer2.nextToken(), tokenizer2.nextToken(), tokenizer2.nextToken());
                courses.add(c);
            }
            Semester sem = new Semester("Semester-" + Integer.toString(i+1), courses);
            semesters.add(sem);
            courses.clear();
        }
        return semesters;
    }
    public void startThread() {
        DownloadThread thread = new DownloadThread();
        thread.start();
    }
    class DownloadThread extends Thread {
        @Override
        public void run() {

            String Response = "";
            try {
                String urlText = "https://edurica.pythonanywhere.com/transcript?rollno=" + rollnumber;
                URL url = null;
                url = new URL(urlText);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine = "";
                StringBuffer responseStream = new StringBuffer();
                while((inputLine = reader.readLine())!= null){
                    responseStream.append(inputLine);
                }
                Response = responseStream.toString();
                // System.out.println(response);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            response = Response;


            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Semester> objects = new ArrayList<Semester>();
                    objects = parse(response);
                    SemesterAdapter adapter = new SemesterAdapter (Transcript.this, R.layout.transcript_semester_object_layout, objects);

                    listView.setAdapter(adapter);
                }
            });


        }
    }
}