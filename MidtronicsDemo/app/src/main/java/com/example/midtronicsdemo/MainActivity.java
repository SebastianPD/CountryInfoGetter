package com.example.midtronicsdemo;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // this gets screen width and heigh in pixels
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        final TextView scrollText = (TextView) this.findViewById(R.id.textView3);
        scrollText.setMovementMethod(new ScrollingMovementMethod());
        StartButton();


        /*ImageView me = new ImageView(this);
        me.setMinimumHeight(width/2);
        me.setMinimumWidth(height/4);
        me.setX(width/2);
        me.setY(height/2);
        me.setImageResource(R.drawable.me)*/

    }
    private void StartButton()
    {
        Button start = (Button) findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListoCountries.class));
            }
        });
    }
}
