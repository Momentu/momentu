package com.momentu.momentuandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView Fahad = (TextView) findViewById(R.id.text);
        Fahad.setText("Fahad Alharbi".toString());
    }
}
