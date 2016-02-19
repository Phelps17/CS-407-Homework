package com.cs407.tylerphelps.cs407hw1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class QuizActivity
        extends AppCompatActivity
    implements QuestionOneFragment.OnAnswerSubmittedListener, QuestionTwoFragment.OnAnswerSubmittedListener

{

    private double score = 0.0;
    private int question = 0;


    public void onAnswerSubmitted(QuestionOne question, Object fragment) {

        if (question.gradeQuestion()) {
            score += 5;
        }

        FragmentManager manager = ((Fragment) fragment).getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove((Fragment) fragment);
        trans.commit();

        // now we would get the next question if it exists
        this.nextQuestion();
    }

    public void onAnswerSubmitted(QuestionTwo question, Object fragment) {

        if (question.gradeQuestion()) {
            score += 5;
        }

        FragmentManager manager = ((Fragment) fragment).getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove((Fragment) fragment);
        trans.commit();

        // now we would get the next question if it exists
        this.nextQuestion();
    }

    private void nextQuestion() {
        this.question++;

        if(question < 3) {
           if (question == 1) {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new QuestionOneFragment()).commit();
            } else if (question == 2) {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new QuestionTwoFragment()).commit();
            }
        }
        else {
            new AlertDialog.Builder(this)
                    .setCancelable(true)
                    .setTitle("GAME OVER")
                    .setMessage("Score " + this.score)
                    .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            finish();
                        }
                    })
                    .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            System.exit(0);
                        }
                    })
                    .show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_quiz);

        super.onCreate(savedInstanceState);
        this.question = 0;

        setContentView(R.layout.activity_quiz);
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            this.nextQuestion();
        }
    }

    protected void onStart() {
        super.onStart();
     //   Toast.makeText(getApplicationContext(), "Your toast message", Toast.LENGTH_LONG).show();
    }

    protected void onRestart(){
        super.onRestart();
    }

    protected void onResume(){
        super.onResume();
    }

    protected void onPause(){
        super.onPause();
    }

    protected void onStop(){
        super.onStop();
    }

    protected void onDestroy(){
        super.onDestroy();
    }


}
