package com.momentu.momentuandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView Fahad = (TextView) findViewById(R.id.text);
        Fahad.setText("Fahad Alharbi\n"+"Ibrahim Alawwad\n"+"Jerry Kobeszko\n"+"Zhen Chen");
    }
}