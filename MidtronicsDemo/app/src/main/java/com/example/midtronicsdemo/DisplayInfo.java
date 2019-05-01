package com.example.midtronicsdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class DisplayInfo extends AppCompatActivity {
 String Url;
 String form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        TextView txtView = (TextView) findViewById(R.id.textView5);
        txtView.setText(message);
        TextView countInfo = (TextView) findViewById(R.id.textView6);
        countInfo.setMovementMethod(new ScrollingMovementMethod());

        String data = null;
         Url = "https://restcountries.eu/rest/v1/name/" + message;


       new RetrieveFeedTask().execute(Url);


    }
    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        private Exception exception;
        HttpURLConnection urlConnection = null;
        String b;
        int count = 0;
        protected String doInBackground(String... params) {
            URL web = null;
            try {
                web = new URL(Url);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                urlConnection = (HttpURLConnection) web.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
           // urlConnection =
          InputStream  a = new BufferedInputStream(urlConnection.getInputStream());

             b = readStream(a);
           // readStream(a);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           // urlConnection.disconnect();
        }
            form = b;
            return b;
        }

        protected void onPostExecute(String feed) {
            // TODO: check this.exception
            // TODO: do something with the feed
            //TextView countInfo = (TextView) findViewById(R.id.textView6);
           // countInfo.setText(feed);
            if (feed == null){
                feed ="";

            }
            new stringMod().execute(feed);
        }
    }

    class stringMod extends AsyncTask<String, Void, String> {



        protected String doInBackground(String... params) {

            ArrayList<String> Storage = new ArrayList<String>(100);
            boolean flag = false;
            String foo = String.format(form);

            int ascii = foo.charAt(2);
            System.out.println(foo);
            System.out.println(ascii);
            String Par = "";


            for (int i = 0; i < foo.length()-1; i++) {

                String thing = foo.substring(i, i + 1);
                int var = thing.charAt(0);


                if (var == 44) {
                    Storage.add(Par);
                    Par = "";
                    continue;
                }


                Par = Par + thing;
            }

            String big = "";
            ArrayList<String> Storage2 = new ArrayList<String>(10);

            for (int i = 0; i < Storage.size(); i++) {



                if (Storage.get(i).contains("area"))
                {
                    big = big + Storage.get(i) + "\n";
                    Storage2.add(Storage.get(i));
                    continue;
                }
                if (Storage.get(i).contains("region"))
                {
                    big = big + Storage.get(i) + "\n";
                    Storage2.add(Storage.get(i));
                    continue;
                }
                if (Storage.get(i).contains("capital"))
                {
                    big = big + Storage.get(i)  + "\n";
                    Storage2.add(Storage.get(i));
                    System.out.println("true");
                    continue;
                }
                if (Storage.get(i).contains("subregion"))
                {
                    big = big + Storage.get(i)  + "\n";
                    Storage2.add(Storage.get(i));
                    continue;
                }
                if (Storage.get(i).contains("population"))
                {
                    big = big + Storage.get(i) + "\n";
                    Storage2.add(Storage.get(i));
                    continue;
                }

            }
            Storage2.trimToSize();
            if (Storage2.size() >= 10){
                big = "";
                big = Storage2.get(5) + "\n" + Storage2.get(6) + "\n" + Storage2.get(7) + "\n" + Storage2.get(8) + "\n" + Storage2.get(9) + "\n";
            }
            System.out.println(Storage2);
            return big;
        }

        protected void onPostExecute(String feed) {
            // TODO: check this.exception
            // TODO: do something with the feed
            TextView countInfo = (TextView) findViewById(R.id.textView6);
            countInfo.setText(feed);
        }
    }
}
