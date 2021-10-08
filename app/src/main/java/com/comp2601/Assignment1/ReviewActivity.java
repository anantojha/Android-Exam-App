package com.comp2601.Assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    String[] answers;
    String[] questions;
    String studentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        TextView mAnswer1TextView = findViewById(R.id.Q1_answer_view);
        TextView mAnswer2TextView = findViewById(R.id.Q2_answer_view);
        TextView mAnswer3TextView = findViewById(R.id.Q3_answer_view);
        TextView mAnswer4TextView = findViewById(R.id.Q4_answer_view);
        TextView mAnswer5TextView = findViewById(R.id.Q5_answer_view);
        TextView mAnswer6TextView = findViewById(R.id.Q6_answer_view);
        TextView mAnswer7TextView = findViewById(R.id.Q7_answer_view);
        TextView mAnswer8TextView = findViewById(R.id.Q8_answer_view);
        TextView mAnswer9TextView = findViewById(R.id.Q9_answer_view);
        TextView mAnswer10TextView = findViewById(R.id.Q10_answer_view);
        Button mContinueSubmitButton = findViewById(R.id.Continue_button);
        Button mBackButton = findViewById(R.id.Back_button);

        answers = getIntent().getStringArrayExtra("answers");
        questions = getIntent().getStringArrayExtra("questions");
        studentEmail = getIntent().getStringExtra("email");

        if(answers[0] != null){
            mAnswer1TextView.setText(R.string.answered);
        } else {
            mAnswer1TextView.setText(R.string.notanswered);
        }
        if(answers[1] != null){
            mAnswer2TextView.setText(R.string.answered);
        } else {
            mAnswer2TextView.setText(R.string.notanswered);
        }
        if(answers[2] != null){
            mAnswer3TextView.setText(R.string.answered);
        } else {
            mAnswer3TextView.setText(R.string.notanswered);
        }
        if(answers[3] != null){
            mAnswer4TextView.setText(R.string.answered);
        } else {
            mAnswer4TextView.setText(R.string.notanswered);
        }
        if(answers[4] != null){
            mAnswer5TextView.setText(R.string.answered);
        } else {
            mAnswer5TextView.setText(R.string.notanswered);
        }
        if(answers[5] != null){
            mAnswer6TextView.setText(R.string.answered);
        } else {
            mAnswer6TextView.setText(R.string.notanswered);
        }
        if(answers[6] != null){
            mAnswer7TextView.setText(R.string.answered);
        } else {
            mAnswer7TextView.setText(R.string.notanswered);
        }
        if(answers[7] != null){
            mAnswer8TextView.setText(R.string.answered);
        } else {
            mAnswer8TextView.setText(R.string.notanswered);
        }
        if(answers[8] != null){
            mAnswer9TextView.setText(R.string.answered);
        } else {
            mAnswer9TextView.setText(R.string.notanswered);
        }
        if(answers[9] != null){
            mAnswer10TextView.setText(R.string.answered);
        } else {
            mAnswer10TextView.setText(R.string.notanswered);
        }

        mContinueSubmitButton.setOnClickListener(v -> {

            Intent intent = new Intent(ReviewActivity.this, SubmitActivity.class);
            intent.putExtra("answers", answers);
            intent.putExtra("questions", questions);
            intent.putExtra("email", studentEmail);
            startActivity(intent);
        });

        mBackButton.setOnClickListener(v -> finish());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
