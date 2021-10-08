package com.comp2601.Assignment1;

import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =  MainActivity.class.getSimpleName();

    private TextView mAnswerATextView;
    private TextView mAnswerBTextView;
    private TextView mAnswerCTextView;
    private TextView mAnswerDTextView;
    private TextView mAnswerETextView;

    private TextView mQuestionTextView;
    private TextView mStudentTextView;
    private ArrayList<Question> questions;
    private Student student;

    private int questionIndex;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("student", student);
        outState.putParcelableArrayList("questions", questions);
        outState.putInt("questionIndex", questionIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //set and inflate UI to manage

        Button mAButton = findViewById(R.id.A_button);
        Button mBButton = findViewById(R.id.B_button);
        Button mCButton = findViewById(R.id.C_button);
        Button mDButton = findViewById(R.id.D_button);
        Button mEButton = findViewById(R.id.E_button);
        Button mPrevButton = findViewById(R.id.prev_button);
        Button mNextButton = findViewById(R.id.next_button);
        Button mSubmitButton = findViewById(R.id.submit_button);

        mAnswerATextView = findViewById(R.id.answerA_text_view);
        mAnswerBTextView = findViewById(R.id.answerB_text_view);
        mAnswerCTextView = findViewById(R.id.answerC_text_view);
        mAnswerDTextView = findViewById(R.id.answerD_text_view);
        mAnswerETextView = findViewById(R.id.answerE_text_view);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setTextColor(Color.BLUE);

        mStudentTextView = findViewById(R.id.student_text_view);
        mStudentTextView.setTextColor(Color.RED);

        mAButton.setOnClickListener(v -> {
            student.getMyAnswers()[questionIndex] = questions.get(questionIndex).getAnswer()[0];
            refreshView();
            mAnswerATextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selected));
        });
        mBButton.setOnClickListener(v -> {
            student.getMyAnswers()[questionIndex] = questions.get(questionIndex).getAnswer()[1];
            refreshView();
            mAnswerBTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selected));
        });
        mCButton.setOnClickListener(v -> {
            student.getMyAnswers()[questionIndex] = questions.get(questionIndex).getAnswer()[2];
            refreshView();
            mAnswerCTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selected));
        });
        mDButton.setOnClickListener(v -> {
            student.getMyAnswers()[questionIndex] = questions.get(questionIndex).getAnswer()[3];
            refreshView();
            mAnswerDTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selected));
        });
        mEButton.setOnClickListener(v -> {
            student.getMyAnswers()[questionIndex] = questions.get(questionIndex).getAnswer()[4];
            refreshView();
            mAnswerETextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selected));
        });

        mPrevButton.setOnClickListener(v -> {

            refreshView();
            if(questionIndex > 0) questionIndex--;
            setQuizActivityView();
            setSavedAnswer();
        });
        mNextButton.setOnClickListener(v -> {
            refreshView();
            if(questionIndex < questions.size()-1) questionIndex++;
            setQuizActivityView();
            setSavedAnswer();
        });

        mSubmitButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
            intent.putExtra("answers", student.getMyAnswers());
            String[] qs = new String[questions.size()];
            for (int i=0; i<qs.length; i++){
                qs[i] = questions.get(i).getQuestionString();
            }
            intent.putExtra("questions", qs);
            intent.putExtra("email", student.getEmail());
            startActivity(intent);
        });

        questions = null;
        ArrayList<Question> parsedModel = null;

        if (savedInstanceState != null) {
            System.out.println("SAVED FROM STATE");
            student = savedInstanceState.getParcelable("student");
            questions = savedInstanceState.getParcelableArrayList("questions");
            questionIndex = savedInstanceState.getInt("questionIndex");
        } else {
            System.out.println("SAVED FROM FILE");
            questionIndex = 0;
            student = new Student("");
            try {
                InputStream iStream = getResources().openRawResource(R.raw.exam);
                BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));
                parsedModel = Exam.pullParseFrom(bReader, student);
                bReader.close();
            }
            catch (java.io.IOException e){
                e.printStackTrace();
            }

            if(parsedModel.isEmpty())
                Log.i(TAG, "ERROR: Questions Not Parsed");

            questions = parsedModel;
        }

        // Display question to view
        setQuizActivityView();
        setSavedAnswer();
    }

    public void setQuizActivityView(){
        if(questions != null && questions.size() > 0) {
            String questionString = "" + (questionIndex + 1) + ") " + questions.get(questionIndex).toString();
            mQuestionTextView.setText(questionString);
            mAnswerATextView.setText(questions.get(questionIndex).getAnswer()[0]);
            mAnswerBTextView.setText(questions.get(questionIndex).getAnswer()[1]);
            mAnswerCTextView.setText(questions.get(questionIndex).getAnswer()[2]);
            mAnswerDTextView.setText(questions.get(questionIndex).getAnswer()[3]);
            mAnswerETextView.setText(questions.get(questionIndex).getAnswer()[4]);
        }

        mStudentTextView.setText(student.getEmail());
    }

    public void setSavedAnswer(){
        if(student.getMyAnswers()[questionIndex] != null){
            if(student.getMyAnswers()[questionIndex].equals(questions.get(questionIndex).getAnswer()[0])){
                mAnswerATextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selected));
            } else if(student.getMyAnswers()[questionIndex].equals(questions.get(questionIndex).getAnswer()[1])) {
                mAnswerBTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selected));
            } else if(student.getMyAnswers()[questionIndex].equals(questions.get(questionIndex).getAnswer()[2])) {
                mAnswerCTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selected));
            } else if(student.getMyAnswers()[questionIndex].equals(questions.get(questionIndex).getAnswer()[3])) {
                mAnswerDTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selected));
            } else if(student.getMyAnswers()[questionIndex].equals(questions.get(questionIndex).getAnswer()[4])) {
                mAnswerETextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selected));
            }
        }
    }

    public void refreshView(){
        mAnswerATextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.notSelected));
        mAnswerBTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.notSelected));
        mAnswerCTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.notSelected));
        mAnswerDTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.notSelected));
        mAnswerETextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.notSelected));
    }
}
