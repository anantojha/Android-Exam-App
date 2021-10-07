package com.comp2601.Assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    private TextView mAnswer1TextView;
    private TextView mAnswer2TextView;
    private TextView mAnswer3TextView;
    private TextView mAnswer4TextView;
    private TextView mAnswer5TextView;
    private TextView mAnswer6TextView;
    private TextView mAnswer7TextView;
    private TextView mAnswer8TextView;
    private TextView mAnswer9TextView;
    private TextView mAnswer10TextView;

    private Button mContinueSubmitButton;

    String[] answers;
    String[] questions;
    String studentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        mAnswer1TextView = (TextView) findViewById(R.id.Q1_answer_view);
        mAnswer2TextView = (TextView) findViewById(R.id.Q2_answer_view);
        mAnswer3TextView = (TextView) findViewById(R.id.Q3_answer_view);
        mAnswer4TextView = (TextView) findViewById(R.id.Q4_answer_view);
        mAnswer5TextView = (TextView) findViewById(R.id.Q5_answer_view);
        mAnswer6TextView = (TextView) findViewById(R.id.Q6_answer_view);
        mAnswer7TextView = (TextView) findViewById(R.id.Q7_answer_view);
        mAnswer8TextView = (TextView) findViewById(R.id.Q8_answer_view);
        mAnswer9TextView = (TextView) findViewById(R.id.Q9_answer_view);
        mAnswer10TextView = (TextView) findViewById(R.id.Q10_answer_view);
        mContinueSubmitButton = (Button) findViewById(R.id.Continue_button);

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

        mContinueSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReviewActivity.this, SubmitActivity.class);
                intent.putExtra("answers", answers);
                intent.putExtra("questions", questions);
                intent.putExtra("email", studentEmail);
                startActivityForResult(intent, 0);

//                if(studentEmail.isEmpty() || studentId.isEmpty()){
//                    Toast.makeText(getApplicationContext(), "Email NOT Sent!", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                String emailSubject = "EXAM SPACE TEST SUBMISSION";
//                String emailBody = createEmailBody(questions, answers, studentName, studentId);
//                EmailService emailService = new EmailService(Config.EMAIL_SENDER_USERNAME, Config.EMAIL_SENDER_PASSWORD, emailSubject, emailBody);
//                emailService.sendEmail(studentEmail);
//                Toast.makeText(getApplicationContext(), "Email Sent!", Toast.LENGTH_LONG).show();
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    };

    private String createEmailBody(String[] questions, String[] answers, String name, String id) {
        String body = "SUBMITTED EXAM: \nSTUDENT: " + name + "\nID: " + id + "\n";

        for(int i = 0; i< questions.length; i++){
            if(answers[i] != null) {
                body += "\nQuestion " + (i + 1) + ": " + questions[i] + "\n" + "Answer: " + answers[i] + "\n";
            } else {
                body += "\nQuestion " + (i + 1) + ": " + questions[i] + "\n" + "Answer: not answered \n";
            }
        }

        return body;
    }
}
