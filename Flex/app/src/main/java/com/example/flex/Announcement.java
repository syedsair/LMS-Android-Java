package com.example.flex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Announcement extends AppCompatActivity {
    String rollnumber = "", response = "";
    private Handler mainHandler = new Handler();
    ListView listView;
    String[] arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        listView = (ListView) findViewById(R.id.Announce_list_view);
        Intent intent = getIntent();
        rollnumber = intent.getStringExtra("rollno");
        startThread();
    }
    public void startThread() {
        DownloadThread thread = new DownloadThread();
        thread.start();
    }
    public ArrayList<String> parse(String response){
        ArrayList<String> data = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(response, "$");
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        return data;
    }
    class DownloadThread extends Thread {
        @Override
        public void run() {

            String Response = "";
            try {
                String urlText = "https://edurica.pythonanywhere.com/announcements";
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
                    ArrayList<String> objects = new ArrayList<String>();
                    ArrayList<announcementObj> ar = new ArrayList<>();
                    objects = parse(response);
                    for (int i=0;i<objects.size();i++){
                        announcementObj obj = new announcementObj();
                        obj.setAnnouncement(objects.get(i));
                        ar.add(obj);
                    }

                    AnnouncementAdapter adapter = new AnnouncementAdapter (Announcement.this, R.layout.announcement_object_layout, ar);
                    listView.setAdapter(adapter);

                }
            });


        }
    }
    public void back(View view){
        Intent intents = new Intent(this, Home.class);
        intents.putExtra("rollno", rollnumber);
        startActivity(intents);
    }
}