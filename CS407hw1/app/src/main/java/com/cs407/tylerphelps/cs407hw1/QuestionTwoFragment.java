package com.cs407.tylerphelps.cs407hw1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RatingBar;

public class QuestionTwoFragment
        extends Fragment
        implements View.OnClickListener{

    OnAnswerSubmittedListener mCallback;
    private QuestionTwo question = new QuestionTwo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentView view = inflater.inflate(R.layout.fragment_rssitem_detail,
        View view = inflater.inflate(R.layout.question_two_fragment,
                container, false);
        return view;
    }

    // Container Activity must implement this interface
    public interface OnAnswerSubmittedListener {
        public void onAnswerSubmitted(QuestionTwo question, Object fragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        if(context instanceof Activity) {
            Activity activity = (Activity) context;
            try {
                this.mCallback = (OnAnswerSubmittedListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        } else {
            throw new ClassCastException("context is not activity");
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RadioButton res1 = (RadioButton) getActivity().findViewById(R.id.radio1);
        res1.setOnClickListener(this);

        res1 = (RadioButton) getActivity().findViewById(R.id.radio2);
        res1.setOnClickListener(this);

        res1 = (RadioButton) getActivity().findViewById(R.id.radio3);
        res1.setOnClickListener(this);
    }

    public void onClick(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();
        int choice = -1;

        // Check which radio button was clicked
        if(checked) {
            switch (v.getId()) {
                case R.id.radio1:
                        choice = 1;
                        break;
                case R.id.radio2:
                        choice = 2;
                        break;
                case R.id.radio3:
                        choice = 3;
                        break;
            }
            this.question.setUserAnswer(choice);
            this.mCallback.onAnswerSubmitted(this.question, this);
        }
    }

    public void onStart() {
        super.onStart();
    }

    // public void onRestart(){
//        super.onRestart();
    //  }

    public void onResume(){
        super.onResume();
    }

    public void onPause(){
        super.onPause();
    }

    public void onStop(){
        super.onStop();
    }

    public void onDestroy(){
        super.onDestroy();
        this.question = null;
        this.mCallback = null;
    }
}
