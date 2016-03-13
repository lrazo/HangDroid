package com.beginner.hangdroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private int mPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int points = getIntent().getIntExtra("POINTS_IDENTIFIER",0);

        mPoints = points;

        TextView textViewPoints = (TextView) findViewById(R.id.textViewPoints);

        textViewPoints.setText(String.valueOf(points));



//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void saveScore(View v){

        SharedPreferences preferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);

        EditText editText = (EditText) findViewById(R.id.editTextName);

        String name = editText.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();

        String previousScores = preferences.getString("SCORES","");

        editor.putString("SCORES", name + " \t " + mPoints + " points\n" + previousScores);

        editor.commit();

        finish();

    }


}
