package com.example.healthapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;

/**
 * Displays the result of the survey and gives the user two options to either discard the result or add it to their history.
 * @author Jimi Takamäki
 */
public class VastausActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.project.MESSAGE";
    private String vastausNimi;
    private String vastausKuvaus;

    @Override

    /**
     * Gets the name and description of the relaxing activity from the intent.
     * Sets the name and description on their respective TextViews.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vastaus);

        Intent intent = getIntent();
        vastausNimi = intent.getStringExtra(KyselyActivity.EXTRA_MESSAGE);
        vastausKuvaus = intent.getStringExtra(KyselyActivity.EXTRA_MESSAGE2);

        TextView textView1 = findViewById(R.id.vastausNimi);
        TextView textView2 = findViewById(R.id.vastausKuvaus);
        textView1.setText(vastausNimi);
        textView2.setText(vastausKuvaus);
    }

    /**
     * Reacts to button presses when either the user decides to discard the result or to accept it.
     * @param view the button pressed.
     */
    public void onButtonPress(View view) {
        if (view == findViewById(R.id.suorita)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, vastausNimi);

            JSONArray historia = (JSONArray)MemManager.instance.get("historia");

            Log.d("VARASTO", "test " + vastausNimi);

            MemManager.instance.add("historia", historia.put(vastausNimi));


            startActivity(intent);
        } else if (view == findViewById(R.id.peruuta)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}