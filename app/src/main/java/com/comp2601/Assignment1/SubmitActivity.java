package com.comp2601.Assignment1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubmitActivity extends AppCompatActivity {


    private EditText mStudentNameEdit;
    private EditText mStudentNumberEdit;
    private TextView mStudentEmailView;
    private Button mFinalSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        mStudentNameEdit = (EditText) findViewById(R.id.student_name_edit);
        mStudentNumberEdit = (EditText) findViewById(R.id.student_id_edit);
        mStudentEmailView = (TextView) findViewById(R.id.student_email_view) ;
        mFinalSubmitButton = (Button) findViewById(R.id.final_submit_button);

        String[] answers = getIntent().getStringArrayExtra("answers");
        String[] questions = getIntent().getStringArrayExtra("questions");
        String studentEmail = getIntent().getStringExtra("email");

        mStudentEmailView.setText(studentEmail);
        mStudentEmailView.setTextColor(Color.BLACK);

        mFinalSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mStudentNameEdit.getText().toString().isEmpty() || mStudentNumberEdit.getText().toString().isEmpty()){
                    if(mStudentNameEdit.getText().toString().isEmpty()) {
                        mStudentNameEdit.setError("Empty");
                    }
                    if(mStudentNumberEdit.getText().toString().isEmpty()) {
                        mStudentNumberEdit.setError("Empty");
                    }

                    Toast.makeText(getApplicationContext(), "Email NOT Sent!", Toast.LENGTH_LONG).show();
                    return;
                }

                String emailSubject = "EXAM SPACE TEST SUBMISSION";
                String emailBody = createEmailBody(questions, answers, mStudentNameEdit.getText().toString(), mStudentNumberEdit.getText().toString());
                EmailService emailService = new EmailService(Config.EMAIL_SENDER_USERNAME, Config.EMAIL_SENDER_PASSWORD, emailSubject, emailBody);
                emailService.sendEmail(studentEmail);
                Toast.makeText(getApplicationContext(), "Email Sent!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SubmitActivity.this, ConfirmationActivity.class);
                startActivityForResult(intent, 0);
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
