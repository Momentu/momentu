package com.momentu.momentuandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

public class TermAndPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_policy);

        TextView aboutTextView = (TextView) findViewById(R.id.term_and_policy_text_view);

        Spanned aboutText = Html.fromHtml("<h1>Terms of Service</h1>"
                + getString(R.string.term_and_policy_text));
        aboutTextView.setText(aboutText);
    }
}
