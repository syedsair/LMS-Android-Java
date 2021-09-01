package com.example.flex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String rollnumber, password;
    int correct = -1;
    private Handler mainHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correct = -1;
    }

    public boolean is_valid(String roll, String password){
        String response = "";
        try {
            String urlText = "https://edurica.pythonanywhere.com/validate?rollno=" + roll + "&pass=" + password;
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
            response = responseStream.toString();
            System.out.println(response);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.equals("Correct"))
            return true;
        else
            return false;

    }
    public void authenticate(View view) {
        TextView rt = (TextView) findViewById(R.id.rollno);
        TextView pt = (TextView) findViewById(R.id.password);
        rollnumber = rt.getText().toString();
        password = pt.getText().toString();
        startThread();

    }
    public void startThread() {
        DownloadThread thread = new DownloadThread();
        thread.start();
    }
    class DownloadThread extends Thread {
        @Override
        public void run() {
            if (is_valid(rollnumber, password)){
                Intent intent = new Intent(MainActivity.this, Home.class);
                // System.out.println("First" + rollnumber);
                intent.putExtra("rollno", rollnumber);
                startActivity(intent);
            }
            else {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}