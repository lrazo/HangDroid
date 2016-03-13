package com.beginner.hangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameMultiActivity extends AppCompatActivity {

    private String mWord = "WORD";

    private int mFailCounter = 0;

    private int mGuessedLetters = 0;

    private int mPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String wordSent = getIntent().getStringExtra("WORD_IDENTIFIER");

        Log.d("MYLOG",wordSent);

        mWord = wordSent;

        createTextView(wordSent);

        //setRandomWord();

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void createTextView(String wordSent) {

        int wordLength = wordSent.length();

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        layoutLetters.removeAllViews();

        for (int i = 0; i < wordLength ; i++) {
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.textview,null);
            layoutLetters.addView(textView);
        }


    }

    /**
     * Retrieving the letter introduced on the editText
     *
     * @param v (button clicked)
     */
    public void introduceLetter(View v){
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);

        String letter = myEditText.getText().toString();

        myEditText.setText("");

        Log.d("MYLOG", "The letter is " + letter);

        if(letter.length() == 1){
            checkLetter(letter.toUpperCase());
        }else{
            Toast.makeText(this,"Please introduce a letter",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Checking if the letter introduced matches any letter in the word to guess
     *
     * @param introducedLetter letter introduced by the user
     */

    public void checkLetter(String introducedLetter){

        char charIntroduced  = introducedLetter.charAt(0);

        boolean letterGuessed = false;

        for (int i = 0; i < mWord.length() ; i++) {

            char charFromTheWord = mWord.charAt(i);

            Log.i("MYLOG", "The letter we are checking is "+charFromTheWord);

            if(charFromTheWord == charIntroduced ){

                Log.i("MYLOG","There is one match");

                showLettersAtIndex(i, charIntroduced);

                letterGuessed = true;

                mGuessedLetters++;
            }

        }

        if(!letterGuessed){
            letterFailed(Character.toString(charIntroduced));
        }

        if(mGuessedLetters == mWord.length()){
            finish();

        }
    }


    public void clearScreen(){
        TextView textViewFailed = (TextView) findViewById(R.id.textView6);

        textViewFailed.setText("");

        mGuessedLetters = 0;

        mFailCounter = 0;

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        int n = layoutLetters.getChildCount();

        for (int i = 0; i < n ; i++) {

            TextView currentTextView = (TextView) layoutLetters.getChildAt(i);

            currentTextView.setText("_");
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setImageResource(R.drawable.hangdroid_0);

    }



    public void letterFailed(String letterFailed){

        TextView textViewFailed = (TextView) findViewById(R.id.textView6);

        String previousFail  = textViewFailed.getText().toString();

        textViewFailed.setText(previousFail + letterFailed);

        mFailCounter++;

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        int resId = -1;

        switch (mFailCounter){
            case 0: resId = R.drawable.hangdroid_0; break;
            case 1: resId = R.drawable.hangdroid_1; break;
            case 2: resId = R.drawable.hangdroid_2; break;
            case 3: resId = R.drawable.hangdroid_3; break;
            case 4: resId = R.drawable.hangdroid_4; break;
            case 5: resId = R.drawable.hangdroid_5; break;
            case 6:
            default:
/*                Intent gameOverActivity = new Intent(this,GameOverActivity.class);
                gameOverActivity.putExtra("POINTS_IDENTIFIER",mPoints);

                startActivity(gameOverActivity);*/
                finish();
                return;
        }

        imageView.setImageResource(resId);
    }

    /**
     * Displaying a letter guessed by the user
     *
     * @param position of the letter
     * @param letterGuessed
     */
    public void showLettersAtIndex(int position,char letterGuessed){
        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        TextView textView = (TextView) layoutLetters.getChildAt(position);

        textView.setText(Character.toString(letterGuessed));

    }

}
