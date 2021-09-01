package com.example.flex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Marks extends AppCompatActivity {
    String rollnumber = "", response = "";
    public MarksListAdapter mAdapter;
    private Handler mainHandler = new Handler();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        listView = (ListView) findViewById(R.id.marks_list);
        Intent intent = getIntent();
        rollnumber = intent.getStringExtra("rollno");

        startThread();
    }
    public void startThread() {
        DownloadThread thread = new DownloadThread();
        thread.start();
    }
    public ArrayList<MarksObject> parse(String response){
        ArrayList<String> data = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(response, "%%");
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        ArrayList<MarksObject> objects = new ArrayList<MarksObject>();
        for (int i=0;i<data.size();i++){
            StringTokenizer tokenizer1 = new StringTokenizer(data.get(i), "$");
            MarksObject obj = new MarksObject(tokenizer1.nextToken(), tokenizer1.nextToken(), tokenizer1.nextToken(), tokenizer1.nextToken());
            objects.add(obj);
        }
        return objects;
    }
    class DownloadThread extends Thread {
        @Override
        public void run() {

            String Response = "";
            try {
                String urlText = "https://edurica.pythonanywhere.com/marks?rollno=" + rollnumber;
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
                    ArrayList<MarksObject> objects = new ArrayList<MarksObject>();
                    objects = parse(response);
                    MarksListAdapter adapter = new MarksListAdapter (Marks.this, R.layout.marks_object_layout, objects);
                    mAdapter = adapter;
                    listView.setAdapter(adapter);
                }
            });


        }
    }
    public void goToHomeFromMarks(View view){
        Intent intents = new Intent(this, Home.class);
        intents.putExtra("rollno", rollnumber);
        startActivity(intents);
    }

}