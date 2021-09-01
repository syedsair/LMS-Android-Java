package com.example.flex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StudentProfile extends AppCompatActivity {
    String rollnumber = "";
    private Handler mainHandler = new Handler();
    TextView rn;
    TextView sec;
    TextView bat;
    TextView deg;
    TextView st;
    TextView name;
    TextView gname;
    TextView cnic;
    TextView dob;
    TextView mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        Intent intent = getIntent();
        rollnumber = intent.getStringExtra("rollno");
        System.out.println(rollnumber);
        rn = (TextView)findViewById(R.id.rollno) ;
        sec = (TextView) findViewById(R.id.section);

        bat = (TextView)findViewById(R.id.batch);
        deg = (TextView)findViewById(R.id.degree);
        st = (TextView)findViewById(R.id.status);
        name = (TextView)findViewById(R.id.student_name);
        gname = (TextView)findViewById(R.id.guardian_name);
        cnic = (TextView)findViewById(R.id.student_CNIC);
        dob = (TextView)findViewById(R.id.student_DOB);
        mob = (TextView)findViewById(R.id.student_mobile_no_);

        startThread();
    }
    public void startThread() {
        DownloadThread thread = new DownloadThread();
        thread.start();
    }
    class DownloadThread extends Thread {
        @Override
        public void run() {
            String response = "";
            ArrayList<String> data = new ArrayList<String>();

            try {
                String urlText = "https://edurica.pythonanywhere.com/personaldetails?rollno=" + rollnumber;
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
            data = parse(response);

            ArrayList<String> finalData = data;
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    name.setText(finalData.get(0));
                    gname.setText(finalData.get(1));
                    cnic.setText(finalData.get(2));
                    dob.setText(finalData.get(3));
                    mob.setText(finalData.get(4));
                    rn.setText(finalData.get(5));
                    sec.setText(finalData.get(6));
                    bat.setText(finalData.get(7));
                    deg.setText(finalData.get(8));
                    st.setText(finalData.get(9));                }
            });


        }
    }

    public void goToHome(View view){
        Intent intents = new Intent(this, Home.class);
        intents.putExtra("rollno", rollnumber);
        startActivity(intents);
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
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());
        data.add(tokenizer.nextToken());

        return data;
    }

}